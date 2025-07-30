package dev.niamhdoyle.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import dev.niamhdoyle.recordkeeper.MainActivity
import dev.niamhdoyle.recordkeeper.databinding.FragmentCyclingBinding
import dev.niamhdoyle.recordkeeper.editrecord.EditRecordActivity
import dev.niamhdoyle.recordkeeper.editrecord.INTENT_EXTRA_SCREEN_DATA

class CyclingFragment : Fragment() {

    private lateinit var vb: FragmentCyclingBinding
    private val activityName = MainActivity.ActivityTypeE.CYCLING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vb = FragmentCyclingBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val runningPreferences = requireContext().getSharedPreferences("cycling", Context.MODE_PRIVATE)
        vb.textViewLongestRideValue.text = runningPreferences.getString("Longest Ride_record", null)
        vb.textViewBiggestClimbValue.text = runningPreferences.getString("Biggest Climb_record", null)
        vb.textViewBestAvgSpeedValue.text = runningPreferences.getString("Best Average Speed_record", null)
        vb.textViewLongestRideDate.text = runningPreferences.getString("Longest Ride_date", null)
        vb.textViewBiggestClimbDate.text = runningPreferences.getString("Biggest Climb_date", null)
        vb.textViewBestAvgSpeedDate.text = runningPreferences.getString("Best Average Speed_date", null)
    }

    private fun getName(heading: TextView): String {
        return heading.text.toString()
    }

    private fun setupClickListeners() {
        vb.containerLongestRide.setOnClickListener {
            launchCyclingRecordScreen(getName(vb.textViewLongestRideHeading), "Distance")
        }
        vb.containerBiggestClimb.setOnClickListener {
            launchCyclingRecordScreen(getName(vb.textViewBiggestClimbHeading), "Height")
        }
        vb.containerBestAvgSpeed.setOnClickListener {
            launchCyclingRecordScreen(getName(vb.textViewBestAvgSpeedHeading), "Avg. speed")
        }
    }

    private fun launchCyclingRecordScreen(recordName: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(recordName, activityName.value, recordFieldHint))
        startActivity(intent)
    }
}