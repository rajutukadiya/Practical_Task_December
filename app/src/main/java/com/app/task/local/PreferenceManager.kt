package com.app.task.local

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(val context: Context) {
    private val pref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    init {
        editor.apply()
    }

    companion object {
        private const val PREF_NAME = "ecommerce"

        private object Key {
            const val USEREMAILANDUSERNAME = "useremailandname"
             const val USERPASSWORD = "password"
             const val USERALREADYLOGINORNOT = "useralreadyloginornot"

        }
    }

    var useralreadyloginornot:Boolean
        get() = pref.getBoolean(Key.USERALREADYLOGINORNOT,false)
        set(useralreadyloginornot) {
            editor.putBoolean(Key.USERALREADYLOGINORNOT, useralreadyloginornot)
            editor.commit()
        }
    var useremailandname: String
        get() = pref.getString(Key.USEREMAILANDUSERNAME, "") ?: ""
        set(useremailandname) {
            editor.putString(Key.USEREMAILANDUSERNAME, useremailandname)
            editor.commit()
        }
    var userpassword: String
        get() = pref.getString(Key.USERPASSWORD, "") ?: ""
        set(userpassword) {
            editor.putString(Key.USERPASSWORD, userpassword)
            editor.commit()
        }


    fun logOut() {
        editor.remove(Key.USERALREADYLOGINORNOT)
        editor.commit()
        editor.apply()
    }


}