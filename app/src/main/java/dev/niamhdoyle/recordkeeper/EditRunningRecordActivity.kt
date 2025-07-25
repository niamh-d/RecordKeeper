package dev.niamhdoyle.recordkeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.niamhdoyle.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var vb: ActivityEditRunningRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(vb.root)

        setSupportActionBar(findViewById(R.id.toolbar))

        title = "My custom title"
    }
}