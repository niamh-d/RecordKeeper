package dev.niamhdoyle.recordkeeper

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import dev.niamhdoyle.recordkeeper.cycling.CyclingFragment
import dev.niamhdoyle.recordkeeper.databinding.ActivityMainBinding
import dev.niamhdoyle.recordkeeper.running.RunningFragment

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    enum class ActivityTypeE(val value: String) {RUNNING("running"), CYCLING("cycling")}

    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vb.bottomNav.setOnItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    private fun clearRecords(type: ActivityTypeE) {
        getSharedPreferences(type.value, Context.MODE_PRIVATE).edit { clear() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menClickHandled = when (item.itemId) {
            R.id.item_reset_running -> {
                clearRecords(ActivityTypeE.RUNNING)
                true
            }

            R.id.item_reset_cycling -> {
                clearRecords(ActivityTypeE.CYCLING)
                true
            }

            R.id.item_reset_all_records -> {
                clearRecords(ActivityTypeE.RUNNING)
                clearRecords(ActivityTypeE.CYCLING)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }

        when (vb.bottomNav.selectedItemId) {
            R.id.nav_running -> onRunningClicked()
            R.id.nav_cycling -> onCyclingClicked()
            else -> {}
        }

        return menClickHandled
    }

    override fun onNavigationItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.nav_cycling -> {
                onCyclingClicked()
                true
            }
            R.id.nav_running -> {
                onRunningClicked()
                true
            }
            else -> false
            }


    private fun onRunningClicked() {
        supportFragmentManager.commit { replace(R.id.fragment_container, RunningFragment()) }
    }

    private fun onCyclingClicked() {
        supportFragmentManager.commit { replace(R.id.fragment_container, CyclingFragment()) }
    }
}