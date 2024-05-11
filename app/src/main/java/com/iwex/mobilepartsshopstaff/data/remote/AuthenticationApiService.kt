package com.iwex.mobilepartsshopstaff.data.remote

import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.AUTHENTICATION_MAPPING_V1
import com.iwex.mobilepartsshopstaff.data.remote.dto.authentication.AuthenticationRequestDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.authentication.AuthenticationResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApiService {

    @POST(AUTHENTICATION_MAPPING_V1)
    suspend fun authenticateStaffOrAdmin(@Body requestDto: AuthenticationRequestDto): AuthenticationResponseDto
}