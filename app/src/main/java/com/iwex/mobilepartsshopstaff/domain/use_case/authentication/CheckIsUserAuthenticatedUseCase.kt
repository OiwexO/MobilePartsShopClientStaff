package com.iwex.mobilepartsshopstaff.domain.use_case.authentication

import com.iwex.mobilepartsshopstaff.domain.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class CheckIsUserAuthenticatedUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend operator fun invoke(): Result<Boolean> {
        return authenticationRepository.isAuthenticated()
    }
}