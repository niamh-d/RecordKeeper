package dev.niamhdoyle.recordkeeper

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationBarView
import dev.niamhdoyle.recordkeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vb.bottomNav.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_cycling -> {
                onCyclingClicked()
                return true
            }
            R.id.nav_running -> {
                onRunningClicked()
                return true
            }
            else -> return false
            }
    }

    private fun onRunningClicked() {
        supportFragmentManager.commit { replace(R.id.fragment_container, RunningFragment()) }
    }

    private fun onCyclingClicked() {
        supportFragmentManager.commit { replace(R.id.fragment_container, CyclingFragment()) }
    }
}