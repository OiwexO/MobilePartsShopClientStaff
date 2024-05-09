package com.iwex.mobilepartsshopstaff.data.remote.interceptor

import com.iwex.mobilepartsshopstaff.domain.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) : Interceptor {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            authenticationRepository.getJwt()
        }
        val request = chain.request().newBuilder()
        request.addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE ${token.value}")
        return chain.proceed(request.build())
        //TODO implement logout
        /*val response = chain.proceed(request.build())
        if (response.code == HTTP_FORBIDDEN) {
            runBlocking {
                authenticationRepository.logout()
            }
        }
        return response*/
    }
}