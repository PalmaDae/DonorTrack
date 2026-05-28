package com.example.data.repository

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("donor_track_prefs", Context.MODE_PRIVATE)

    private val _isLoggedIn = MutableStateFlow(prefs.getBoolean("KEY_IS_LOGGED_IN", false))
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()


    fun setAuthorized(auth: Boolean) {
        prefs.edit().putBoolean("KEY_IS_LOGGED_IN", auth).apply()
        _isLoggedIn.value = auth
    }

    fun saveAuthStatus(loggedIn: Boolean) {
        setAuthorized(loggedIn)
    }

    fun clearSession() {
        prefs.edit().clear().apply()
        _isLoggedIn.value = false
    }
}