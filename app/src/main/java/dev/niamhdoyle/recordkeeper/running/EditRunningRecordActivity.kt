package dev.niamhdoyle.recordkeeper.running

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import dev.niamhdoyle.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var vb: ActivityEditRunningRecordBinding
    private lateinit var runningPreferences: SharedPreferences
    private val recordName: String? by lazy { intent.getStringExtra("distance") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(vb.root)

        title = formatTitle()

        displayRecord()
        vb.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }
    }

    private fun displayRecord() {
        runningPreferences = getSharedPreferences("running", Context.MODE_PRIVATE)
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

    private fun formatTitle(): String {
        return "Edit $recordName record"
    }
}