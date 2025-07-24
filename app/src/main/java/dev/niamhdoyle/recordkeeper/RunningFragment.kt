package dev.niamhdoyle.recordkeeper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    private fun setupClickListeners() {
        vb.container5km.setOnClickListener {
            launchRunningRecordScreen()
        }
        vb.container10km.setOnClickListener {
            launchRunningRecordScreen()
        }
        vb.containerHalfMarathon.setOnClickListener {
            launchRunningRecordScreen()
        }
        vb.containerMarathon.setOnClickListener {
            launchRunningRecordScreen()
        }
    }

    private fun launchRunningRecordScreen() {
        val intent = Intent(context, EditRunningRecordActivity::class.java)
        startActivity(intent)
    }
}