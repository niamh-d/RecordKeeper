package dev.niamhdoyle.recordkeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
    }

    private fun formatTitle(): String {
        return "Edit $recordName record"
    }
}