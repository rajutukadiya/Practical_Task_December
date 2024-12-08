package com.app.task.activity.spalshscreen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.app.task.MainActivity
import com.app.task.R
import com.app.task.activity.loginscreen.LoginScreenActivity
import com.app.task.databinding.ActivitySplashscreenBinding
import com.app.task.local.PreferenceManager
import com.practicaltask.customview.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : BaseActivity() {
    private lateinit var activitysplashscreenBinding: ActivitySplashscreenBinding

    var pref: PreferenceManager? = null
    override fun MyView(): Int {
        return R.layout.activity_splashscreen
    }

    override fun initialization() {
        activitysplashscreenBinding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(activitysplashscreenBinding.root)
        pref = PreferenceManager(this@SplashscreenActivity)
    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun prepareview() {
        loadSplashScreen()

    }

    override fun listener() {

    }

    /*
     Load splash screen
     */
    fun loadSplashScreen() {
        Handler(Looper.myLooper()!!).postDelayed({
            if (pref?.useralreadyloginornot == true) {
                openActivity(this, MainActivity::class.java, false)
            } else {
                openActivity(this, LoginScreenActivity::class.java, false)
            }
            finish()

        }, 5000)
    }
}
