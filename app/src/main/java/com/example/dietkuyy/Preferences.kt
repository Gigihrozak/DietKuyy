package com.example.dietkuyy

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object Preferences {

    const val KEY_USER_TEREGISTER = "user"
    const val KEY_PASS_TEREGISTER = "pass"
    const val KEY_USERNAME_SEDANG_LOGIN = "Username_logged_in"
    const val KEY_STATUS_SEDANG_LOGIN = "Status_logged_in"
    private fun getSharedPreference(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @JvmStatic
    fun setRegisteredUser(context: Context, username: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_USER_TEREGISTER, username)
        editor.apply()
    }

    @JvmStatic
    fun getRegisteredUser(context: Context): String? {
        return getSharedPreference(context).getString(KEY_USER_TEREGISTER, "")
    }

    @JvmStatic
    fun setRegisteredPass(context: Context, password: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_PASS_TEREGISTER, password)
        editor.apply()
    }

    fun getRegisteredPass(context: Context): String? {
        return getSharedPreference(context).getString(KEY_PASS_TEREGISTER, "")
    }

    fun setLoggedInUser(context: Context, username: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_USERNAME_SEDANG_LOGIN, username)
        editor.apply()
    }

    fun getLoggedInUser(context: Context): String? {
        return getSharedPreference(context).getString(KEY_USERNAME_SEDANG_LOGIN, "")
    }

    fun setLoggedInStatus(context: Context, status: Boolean) {
        val editor = getSharedPreference(context).edit()
        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN, status)
        editor.apply()
    }

    fun getLoggedInStatus(context: Context): Boolean {
        return getSharedPreference(context).getBoolean(KEY_STATUS_SEDANG_LOGIN, false)
    }

    fun clearLoggedInUser(context: Context) {
        val editor = getSharedPreference(context).edit()
        editor.remove(KEY_USERNAME_SEDANG_LOGIN)
        editor.remove(KEY_STATUS_SEDANG_LOGIN)
        editor.apply()
    }
}