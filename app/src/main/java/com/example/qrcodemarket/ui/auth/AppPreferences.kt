package com.example.qrcodemarket.ui.auth

import android.content.Context
import android.content.SharedPreferences


object AppPreferences {
    private const val NAME = "QRMarketApplication"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    //SharedPreferences variables
    private val IS_LOGIN = Pair("is_login", false)
    private val CHECKIN = Pair("is_checkin", false)
    private val CHECKEND = Pair("check_end", false)
    private val CHECKOUT = Pair("check_out", false)
    private val USERNAME = Pair("username", "")
    private val PASSWORD = Pair("password", "")
    private val FULLNAME = Pair("fullname", "")
    private val ROLE = Pair("role", "")
    private val CITIZENID = Pair("citizenId", "")
    private val CHECKSOUND = Pair("checksound", false)
    private val CHECKVIBRATE = Pair("checkvibrate", false)


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    //an inline function to put variable and save it
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    //SharedPreferences variables getters/setters
    var isLogin: Boolean
        get() = preferences.getBoolean(IS_LOGIN.first, IS_LOGIN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGIN.first, value)
        }
    var checkin: Boolean
        get() = preferences.getBoolean(CHECKIN.first, CHECKIN.second)
        set(value) = preferences.edit {
            it.putBoolean(CHECKIN.first, value)
        }

    var checkend: Boolean
        get() = preferences.getBoolean(CHECKEND.first, CHECKEND.second)
        set(value) = preferences.edit {
            it.putBoolean(CHECKEND.first, value)
        }

    var checkout: Boolean
        get() = preferences.getBoolean(CHECKOUT.first, CHECKOUT.second)
        set(value) = preferences.edit {
            it.putBoolean(CHECKOUT.first, value)
        }

    var username: String
        get() = preferences.getString(USERNAME.first, USERNAME.second) ?: ""
        set(value) = preferences.edit {
            it.putString(USERNAME.first, value)
        }

    var password: String
        get() = preferences.getString(PASSWORD.first, PASSWORD.second) ?: ""
        set(value) = preferences.edit {
            it.putString(PASSWORD.first, value)
        }

    var fullname: String
        get() = preferences.getString(FULLNAME.first, FULLNAME.second) ?: ""
        set(value) = preferences.edit {
            it.putString(FULLNAME.first, value)
        }

    var role: String
        get() = preferences.getString(ROLE.first, ROLE.second) ?: ""
        set(value) = preferences.edit {
            it.putString(ROLE.first, value)
        }
    var citizenId: String
        get() = preferences.getString(CITIZENID.first, CITIZENID.second) ?: ""
        set(value) = preferences.edit {
            it.putString(CITIZENID.first, value)
        }
    var checksound: Boolean
        get() = preferences.getBoolean(CHECKSOUND.first, CHECKSOUND.second)
        set(value) = preferences.edit {
            it.putBoolean(CHECKSOUND.first, value)
        }
    var checkvibrate: Boolean
        get() = preferences.getBoolean(CHECKVIBRATE.first, CHECKVIBRATE.second)
        set(value) = preferences.edit {
            it.putBoolean(CHECKVIBRATE.first, value)
        }
}