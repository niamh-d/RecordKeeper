package dev.niamhdoyle.recordkeeper.cycling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.niamhdoyle.recordkeeper.databinding.ActivityEditCyclingRecordBinding

class EditCyclingRecordActivity : AppCompatActivity() {

    private lateinit var vb: ActivityEditCyclingRecordBinding
    private lateinit var recordName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityEditCyclingRecordBinding.inflate(layoutInflater)
        setContentView(vb.root)

        recordName = intent.getStringExtra("distance").toString()

        title = formatTitle()
    }

    private fun formatTitle(): String {
        return "Edit $recordName record"
    }
}