package com.app.task

import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.app.task.activity.loginscreen.LoginScreenActivity
import com.app.task.databinding.ActivityMainBinding
import com.app.task.fragment.MedicineDataListFragment
import com.app.task.local.PreferenceManager
import com.app.task.utils.AppUtils
import com.app.task.utils.getTimeMessage
import com.practicaltask.customview.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    var pref: PreferenceManager? = null
    override fun MyView(): Int {
        return R.layout.activity_main
    }

    override fun initialization() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = PreferenceManager(this@MainActivity)

    }

    override fun prepareview() {
        if (AppUtils.isInterConnectionIsAvailable(this@MainActivity)) {
            loadFragment(MedicineDataListFragment())
        } else {
            AppUtils.showErrorDialog(
                this@MainActivity,
                resources.getString(R.string.internetNotAvailableStr)
            )
        }
    }

    override fun listener() {
        setData()
        setTitleBar()
    }

    /*
     set title of screen
     */
    private fun setTitleBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.home)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /*
    Load Home fragment inside of main activity
     */
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null) // Add to back stack if needed
        transaction.commit()
    }

    /*
       set Current time and set Good morning, Good afternoon, Good evening message
     */
    @SuppressLint("SetTextI18n")
    private fun setData() {
        binding.txtTimeMessage.text = getString(R.string.hi) + this.getTimeMessage()
        binding.txtEmailandUsername.text = getString(R.string.loginuser) + pref?.useremailandname

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.action_logout -> {
                pref?.logOut()
                openActivity(this, LoginScreenActivity::class.java, true)
               finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}