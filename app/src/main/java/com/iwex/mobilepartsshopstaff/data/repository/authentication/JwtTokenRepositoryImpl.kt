package com.iwex.mobilepartsshopstaff.data.repository.authentication

import android.content.SharedPreferences
import com.iwex.mobilepartsshopstaff.domain.repository.authentication.JwtTokenRepository
import javax.inject.Inject

class JwtTokenRepositoryImpl @Inject constructor(private val preferences: SharedPreferences) :
    JwtTokenRepository {

    companion object {
        const val KEY_JWT = "jwt"
    }

    override suspend fun saveJwt(token: String) {
        preferences.edit().putString(KEY_JWT, token).apply()
    }

    override suspend fun getJwt(): String? {
        return preferences.getString(KEY_JWT, null)
    }

    override suspend fun clearToken() {
        preferences.edit().remove(KEY_JWT).apply()
    }
}