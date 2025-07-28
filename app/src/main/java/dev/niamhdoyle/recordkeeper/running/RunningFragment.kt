package dev.niamhdoyle.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import dev.niamhdoyle.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment : Fragment() {

    private lateinit var vb: FragmentRunningBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        vb = FragmentRunningBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        displayRecords()
    }

    private fun displayRecords() {
        val runningPreferences = requireContext().getSharedPreferences("running", Context.MODE_PRIVATE)
        vb.textView5kmValue.text = runningPreferences.getString("5km_record", null)
        vb.textView5kmDate.text = runningPreferences.getString("5km_date", null)
        vb.textView10kmValue.text = runningPreferences.getString("10km_record", null)
        vb.textView10kmDate.text = runningPreferences.getString("10km_date", null)
        vb.textViewHalfMarathonValue.text = runningPreferences.getString("half_marathon_record", null)
        vb.textViewHalfMarathonDate.text = runningPreferences.getString("half_marathon_date", null)
        vb.textViewMarathonValue.text = runningPreferences.getString("marathon_record", null)
        vb.textViewMarathonDate.text = runningPreferences.getString("marathon_date", null)
    }

    private fun getName(heading: TextView): String {
        return heading.text.toString()
    }

    private fun setupClickListeners() {
        vb.container5km.setOnClickListener {
            launchRunningRecordScreen(getName(vb.textView5kmHeading))
        }
        vb.container10km.setOnClickListener {
            launchRunningRecordScreen(getName(vb.textView10kmHeading))
        }
        vb.containerHalfMarathon.setOnClickListener {
            launchRunningRecordScreen(getName(vb.textViewHalfMarathonHeading))
        }
        vb.containerMarathon.setOnClickListener {
            launchRunningRecordScreen(getName(vb.textViewMarathonHeading))
        }
    }

    private fun launchRunningRecordScreen(recordName: String) {
        val intent = Intent(context, EditRunningRecordActivity::class.java)
        intent.putExtra("distance", recordName)
        startActivity(intent)
    }
}