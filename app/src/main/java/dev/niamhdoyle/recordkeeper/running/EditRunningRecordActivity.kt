package dev.niamhdoyle.recordkeeper.running

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import dev.niamhdoyle.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var vb: ActivityEditRunningRecordBinding
    private lateinit var recordName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(vb.root)

        recordName = intent.getStringExtra("distance").toString()

        title = formatTitle()

        vb.buttonSave.setOnClickListener {
            saveRecord(recordName)
        }
    }

    private fun saveRecord(recordName: String?) {
        val record = vb.editTextRecord.text.toString()
        val date = vb.editTextDate.text.toString()

        val runningPreferences = getSharedPreferences("running", Context.MODE_PRIVATE)
        runningPreferences.edit {
            putString("${recordName}_record", record)
            putString("${recordName}_date", date)
        }
    }

    private fun formatTitle(): String {
        return "Edit $recordName record"
    }
}