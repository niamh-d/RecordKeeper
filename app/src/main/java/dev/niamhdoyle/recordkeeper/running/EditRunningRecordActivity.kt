package dev.niamhdoyle.recordkeeper.running

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import dev.niamhdoyle.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var vb: ActivityEditRunningRecordBinding
    private val runningPreferences by lazy { getSharedPreferences("running", Context.MODE_PRIVATE) }
    private val recordName by lazy { intent.getStringExtra("distance") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(vb.root)
        setupUi()
        displayRecord()
    }

    private fun setupUi() {
        title = formatTitle()
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
        vb.editTextRecord.setText(runningPreferences.getString("${recordName}_record", null))
        vb.editTextDate.setText(runningPreferences.getString("${recordName}_date", null))
    }

    private fun saveRecord() {
        val record = vb.editTextRecord.text.toString()
        val date = vb.editTextDate.text.toString()

        runningPreferences.edit {
            putString("${recordName}_record", record)
            putString("${recordName}_date", date)
        }
    }

    private fun clearRecord() {
        runningPreferences.edit {
            remove("${recordName}_record")
            remove("${recordName}_date")
        }
    }

    private fun formatTitle(): String {
        return "Edit $recordName record"
    }
}