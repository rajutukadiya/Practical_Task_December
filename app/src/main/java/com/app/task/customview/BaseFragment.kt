package com.practicaltask.customview

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment() {

    var progressDialog: ProgressDialog? = null
    val baseHandler = Handler(Looper.getMainLooper())
    var runnable = Runnable {  }




    override fun onPause() {
        if (progressDialog?.isShowing == true){
            progressDialog?.dismiss()
        }
        super.onPause()
    }

    override fun onDestroy() {
        if (progressDialog?.isShowing == true){
            progressDialog?.dismiss()
        }
        super.onDestroy()
    }
    fun openActivity(context: Activity?, classType: Class<*>?) {
        val it = Intent(context, classType)
        startActivity(it)
    }
}