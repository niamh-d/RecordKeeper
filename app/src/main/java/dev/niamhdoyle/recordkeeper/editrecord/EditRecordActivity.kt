package dev.niamhdoyle.recordkeeper.editrecord

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import dev.niamhdoyle.recordkeeper.databinding.ActivityEditRecordBinding
import java.io.Serializable

const val INTENT_EXTRA_SCREEN_DATA = "screen_data"

class EditRecordActivity : AppCompatActivity() {

    private lateinit var vb: ActivityEditRecordBinding
    private val screenData by lazy {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA, ScreenData::class.java) as ScreenData
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA) as ScreenData
        }
    }
    private val recordPreferences by lazy { getSharedPreferences(screenData.sharedPreferences, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(vb.root)
        setupUi()
        displayRecord()
    }

    private fun setupUi() {
        title = "Edit ${screenData.record} record"
        vb.textInputRecord.hint = screenData.recordFieldHint

        vb.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }
        vb.buttonClear.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun displayRecord() {
        vb.editTextRecord.setText(recordPreferences.getString("${screenData.record}_record", null))
        vb.editTextDate.setText(recordPreferences.getString("${screenData.record}_date", null))
    }

    private fun saveRecord() {
        val record = vb.editTextRecord.text.toString()
        val date = vb.editTextDate.text.toString()

        recordPreferences.edit {
            putString("${screenData.record}_record", record)
            putString("${screenData.record}_date", date)
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("${screenData.record}_record")
            remove("${screenData.record}_date")
        }
    }

    data class ScreenData (
        val record: String,
        val sharedPreferences: String,
        val recordFieldHint: String
    ) : Serializable
}