package com.iwex.mobilepartsshopstaff.domain.repository.authentication

interface JwtTokenRepository {

    suspend fun saveJwt(token: String)

    suspend fun getJwt(): String?

    suspend fun clearToken()
}