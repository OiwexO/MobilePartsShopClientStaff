package com.iwex.mobilepartsshopstaff.data.remote

import com.iwex.mobilepartsshopstaff.domain.repository.authentication.JwtTokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

// https://medium.com/@ratko.kostov21/jwt-authentication-in-android-using-retrofit-and-authenticator-b7b66e231295
/*
class AuthAuthenticator @Inject constructor(
    private val tokenRepository: JwtTokenRepository
) : Authenticator {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }
    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = runBlocking {
            tokenRepository.getJwt()
        }
        synchronized(this) {
            val updatedToken = runBlocking {
                tokenRepository.getJwt()
            }
            val token = if (currentToken != updatedToken) updatedToken else {
                val newSessionResponse = runBlocking { refreshTokenService.refreshToken() }
                if (newSessionResponse.isSuccessful && newSessionResponse.body() != null) {
                    newSessionResponse.body()?.let { body ->
                        runBlocking {
                            tokenManager.saveAccessJwt(body.accessToken)
                            tokenManager.saveRefreshJwt(body.refreshToken)
                        }
                        body.accessToken
                    }
                } else null
            }
            return if (token != null) response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
                .build() else null
        }
    }
}*/
