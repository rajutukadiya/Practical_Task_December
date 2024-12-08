package com.practicaltask.customview

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.app.task.R


abstract class BaseActivity : AppCompatActivity() {
    //handler
    var progressDialog: ProgressDialog? = null

    val baseHandler = Handler(Looper.getMainLooper())
    var runnable = Runnable { }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setStatusColor()
        setContentView(MyView())
        supportActionBar?.hide()
        initialization()
        prepareview()
        listener()


    }

    @SuppressLint("ObsoleteSdkInt")
    private fun setStatusColor() {

        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black)); //status bar or the time bar at the top (see example image1)


        }
    }

    abstract fun MyView(): Int
    abstract fun initialization()
    abstract fun prepareview()
    abstract fun listener()

    fun openActivity(context: Activity?, classType: Class<*>?,type: Boolean) {
        val it = Intent(context, classType)
        if(type)
        {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(it)
    }

}