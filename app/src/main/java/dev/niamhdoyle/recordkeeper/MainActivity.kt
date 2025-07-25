package dev.niamhdoyle.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
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