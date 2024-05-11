package com.iwex.mobilepartsshopstaff.domain.repository.authentication

import com.iwex.mobilepartsshopstaff.domain.entity.authentication.AuthenticationRequest
import com.iwex.mobilepartsshopstaff.domain.entity.authentication.AuthenticationResponse
import com.iwex.mobilepartsshopstaff.domain.entity.authentication.Jwt
import com.iwex.mobilepartsshopstaff.domain.entity.user.User

interface AuthenticationRepository {

    suspend fun authenticateUser(authenticationRequest: AuthenticationRequest): Result<AuthenticationResponse>

    suspend fun isAuthenticated(): Result<Boolean>

    suspend fun getJwt(): Jwt

    suspend fun getUser(): Result<User>

    suspend fun logout(): Result<Unit>
}