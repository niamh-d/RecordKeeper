package dev.niamhdoyle.recordkeeper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import dev.niamhdoyle.recordkeeper.databinding.FragmentCyclingBinding

class CyclingFragment : Fragment() {

    private lateinit var vb: FragmentCyclingBinding

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

    private fun getName(heading: TextView): String {
        return heading.text.toString()
    }

    private fun setupClickListeners() {
        vb.containerLongestRide.setOnClickListener {
            launchRunningRecordScreen(getName(vb.textViewLongestRideHeading))
        }
        vb.containerBiggestClimb.setOnClickListener {
            launchRunningRecordScreen(getName(vb.textViewBiggestClimbHeading))
        }
        vb.containerBestAvgSpeed.setOnClickListener {
            launchRunningRecordScreen(getName(vb.textViewBestAvgSpeedHeading))
        }
    }

    private fun launchRunningRecordScreen(recordName: String) {
        val intent = Intent(context, EditRunningRecordActivity::class.java)
        intent.putExtra("distance", recordName)
        startActivity(intent)
    }
}