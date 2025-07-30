package dev.niamhdoyle.recordkeeper.editrecord

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import dev.niamhdoyle.recordkeeper.databinding.ActivityEditRecordBinding
import java.io.Serializable

class EditRecordActivity : AppCompatActivity() {

    private lateinit var vb: ActivityEditRecordBinding
    private val screenData by lazy {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                INTENT_EXTRA_SCREEN_DATA,
                ScreenData::class.java
            ) as ScreenData
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA) as ScreenData
        }
    }
    private val recordPreferences by lazy {
        getSharedPreferences(
            screenData.sharedPreferences,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(vb.root)
        setupUi()
        displayRecord()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
        vb.editTextRecord.setText(
            recordPreferences.getString(
                "${screenData.record}_$SHARED_PREFERENCES_RECORD_KEY",
                null
            )
        )
        vb.editTextDate.setText(
            recordPreferences.getString(
                "${screenData.record}_$SHARED_PREFERENCES_DATE_KEY",
                null
            )
        )
    }

    private fun saveRecord() {
        val record = vb.editTextRecord.text.toString()
        val date = vb.editTextDate.text.toString()

        recordPreferences.edit {
            putString("${screenData.record}_$SHARED_PREFERENCES_RECORD_KEY", record)
            putString("${screenData.record}_$SHARED_PREFERENCES_DATE_KEY", date)
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("${screenData.record}_$SHARED_PREFERENCES_RECORD_KEY")
            remove("${screenData.record}_$SHARED_PREFERENCES_DATE_KEY")
        }
    }

    data class ScreenData(
        val record: String,
        val sharedPreferences: String,
        val recordFieldHint: String
    ) : Serializable

    companion object {
        const val INTENT_EXTRA_SCREEN_DATA = "screen_data"
        const val SHARED_PREFERENCES_RECORD_KEY = "record"
        const val SHARED_PREFERENCES_DATE_KEY = "date"
    }
}