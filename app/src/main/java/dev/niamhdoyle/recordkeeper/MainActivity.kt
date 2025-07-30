package dev.niamhdoyle.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar
import dev.niamhdoyle.recordkeeper.cycling.CyclingFragment
import dev.niamhdoyle.recordkeeper.databinding.ActivityMainBinding
import dev.niamhdoyle.recordkeeper.running.RunningFragment

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    enum class ActivityTypeE(val value: String) { RUNNING("running"), CYCLING("cycling"), ALL("all") }

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

    private fun clearRecords(type: ActivityTypeE) = when (type) {
            ActivityTypeE.ALL -> {
                getSharedPreferences(ActivityTypeE.RUNNING.value, MODE_PRIVATE).edit { clear() }
                getSharedPreferences(ActivityTypeE.CYCLING.value, MODE_PRIVATE).edit { clear() }
            }
            else -> getSharedPreferences(type.value, MODE_PRIVATE).edit { clear() }
        }


    private fun refreshFragment() {
        when (vb.bottomNav.selectedItemId) {
            R.id.nav_running -> onRunningClicked()
            R.id.nav_cycling -> onCyclingClicked()
            else -> {}
        }
    }

    private fun showSnackbar() {
        val snackbar = Snackbar.make(
            vb.fragmentContainer,
            "Records cleared successfully!",
            Snackbar.LENGTH_LONG
        )
        snackbar.anchorView = vb.bottomNav
        snackbar.show()
    }

    private fun confirmationDialogPopup(type: ActivityTypeE) {
        AlertDialog.Builder(this)
            .setTitle("Reset ${type.value} records")
            .setMessage("Are you sure? This action cannot be undone.")
            .setPositiveButton("Yes, I'm sure") { _, _ ->
                clearRecords(type)
                refreshFragment()
                showSnackbar()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuClickHandled = when (item.itemId) {
            R.id.item_reset_running -> {
                confirmationDialogPopup(ActivityTypeE.RUNNING)
                true
            }

            R.id.item_reset_cycling -> {
                confirmationDialogPopup(ActivityTypeE.CYCLING)
                true
            }

            R.id.item_reset_all_records -> {
                confirmationDialogPopup(ActivityTypeE.ALL)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }

        return menuClickHandled
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