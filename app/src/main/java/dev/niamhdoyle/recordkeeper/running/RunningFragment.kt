package dev.niamhdoyle.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import dev.niamhdoyle.recordkeeper.MainActivity
import dev.niamhdoyle.recordkeeper.databinding.FragmentRunningBinding
import dev.niamhdoyle.recordkeeper.editrecord.EditRecordActivity
import dev.niamhdoyle.recordkeeper.editrecord.EditRecordActivity.Companion.INTENT_EXTRA_SCREEN_DATA

class RunningFragment : Fragment() {

    private lateinit var vb: FragmentRunningBinding
    private val activityType = MainActivity.ActivityTypeE.RUNNING

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
    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val runningPreferences = requireContext().getSharedPreferences(activityType.value, Context.MODE_PRIVATE)
        vb.textView5kmValue.text = runningPreferences.getString("${FIVE_K}_${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        vb.textView5kmDate.text = runningPreferences.getString("${FIVE_K}_${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)
        vb.textView10kmValue.text = runningPreferences.getString("${TEN_K}_${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        vb.textView10kmDate.text = runningPreferences.getString("${TEN_K}_${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)
        vb.textViewHalfMarathonValue.text = runningPreferences.getString("${HALF_MARATHON}_${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        vb.textViewHalfMarathonDate.text = runningPreferences.getString("${HALF_MARATHON}_${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)
        vb.textViewMarathonValue.text = runningPreferences.getString("${MARATHON}_${EditRecordActivity.SHARED_PREFERENCES_RECORD_KEY}", null)
        vb.textViewMarathonDate.text = runningPreferences.getString("${MARATHON}_${EditRecordActivity.SHARED_PREFERENCES_DATE_KEY}", null)
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

    private fun launchRunningRecordScreen(distance: String) {
        val intent = Intent(context, EditRecordActivity::class.java)

        intent.putExtra(INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(distance, activityType.value, "Time"))
        startActivity(intent)
    }

    companion object {
        const val TEN_K = "10km"
        const val FIVE_K = "5km"
        const val HALF_MARATHON = "Half Marathon"
        const val MARATHON = "Marathon"
    }
}