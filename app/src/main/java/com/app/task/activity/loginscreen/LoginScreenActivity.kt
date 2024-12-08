package com.app.task.activity.loginscreen

import android.os.Build

import android.widget.Toast
import androidx.annotation.RequiresApi
import com.app.task.MainActivity
import com.app.task.R
import com.app.task.databinding.ActivityLoginBinding
import com.app.task.local.PreferenceManager

import com.practicaltask.customview.BaseActivity

class LoginScreenActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

sdsfds
    sf
    sdf

    var pref: PreferenceManager? = null
    override fun MyView(): Int {
        return R.layout.activity_login
    }

    override fun initialization() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun prepareview() {
        pref = PreferenceManager(this@LoginScreenActivity)
        getRememberMeData()

    }

    /*
       get username and password from shared preference
     */
    fun getRememberMeData() {
        if (pref?.useremailandname?.isNotEmpty() == true && pref!!.userpassword.isNotEmpty()) {
            binding.edtEmailAddress.setText(pref?.useremailandname)
            binding.edtPassword.setText(pref!!.userpassword)
            binding.remebermecheckbox.isChecked = true

        }
        binding.remebermecheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                pref?.useremailandname = ""
                pref?.userpassword = ""
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun listener() {
        binding.imgback.setOnClickListener {
            finish()
        }
        binding.btnsubmit.setOnClickListener {
            login()
        }
    }


    @RequiresApi(Build.VERSION_CODES.S)
    fun login() {

        val emailAddress = binding.edtEmailAddress.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
        /*
          Check validation of login screen
         */
        if (emailAddress.isEmpty()) {
            Toast.makeText(this, getString(R.string.erroremailaddress), Toast.LENGTH_SHORT).show()
            return
        }
        if (password.isEmpty()) {
            Toast.makeText(this, getString(R.string.errorpassword), Toast.LENGTH_SHORT)
                .show()
            return
        }

        /*
           Save login data in shared preference
         */
        if (binding.remebermecheckbox.isChecked) {
            pref?.useremailandname = emailAddress
            pref?.userpassword = password
        } else {
            pref?.useremailandname = ""
            pref?.userpassword = ""
        }
        pref?.useralreadyloginornot = true

        /*
          Open dashboard activity
         */
        openActivity(this, MainActivity::class.java, false)
        finish()

    }


}
