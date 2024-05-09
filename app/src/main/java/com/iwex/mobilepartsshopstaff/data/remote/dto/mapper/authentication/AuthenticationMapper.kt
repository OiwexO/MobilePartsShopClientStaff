package com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.authentication

import com.iwex.mobilepartsshopstaff.data.remote.dto.authentication.AuthenticationRequestDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.authentication.AuthenticationResponseDto
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.user.UserMapper
import com.iwex.mobilepartsshopstaff.domain.entity.authentication.AuthenticationRequest
import com.iwex.mobilepartsshopstaff.domain.entity.authentication.AuthenticationResponse
import com.iwex.mobilepartsshopstaff.domain.entity.authentication.Jwt
import javax.inject.Inject

class AuthenticationMapper @Inject constructor(
    private val userMapper: UserMapper
) : ResponseRequestMapper<AuthenticationResponse, AuthenticationRequest, AuthenticationResponseDto, AuthenticationRequestDto>() {

    override fun toEntity(dto: AuthenticationResponseDto): AuthenticationResponse {
        val user = userMapper.toEntity(dto.user)
        return AuthenticationResponse(
            user = user,
            jwtToken = Jwt(dto.jwtToken),
        )
    }

    override fun toRequestDto(request: AuthenticationRequest): AuthenticationRequestDto {
        return AuthenticationRequestDto(
            username = request.username,
            password = request.password,
        )
    }
}