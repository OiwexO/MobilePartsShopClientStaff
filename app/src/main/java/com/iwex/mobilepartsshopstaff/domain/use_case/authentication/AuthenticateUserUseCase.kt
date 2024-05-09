package com.iwex.mobilepartsshopstaff.domain.use_case.authentication

import com.iwex.mobilepartsshopstaff.domain.entity.authentication.AuthenticationRequest
import com.iwex.mobilepartsshopstaff.domain.entity.authentication.AuthenticationResponse
import com.iwex.mobilepartsshopstaff.domain.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend operator fun invoke(
        authenticationRequest: AuthenticationRequest
    ): Result<AuthenticationResponse> {
        return authenticationRepository.authenticateUser(authenticationRequest)
    }
}