package com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.user

import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.ResponseMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.user.AddressResponseDto
import com.iwex.mobilepartsshopstaff.domain.entity.user.Address

class AddressMapper : ResponseMapper<Address, AddressResponseDto>() {

    override fun toEntity(dto: AddressResponseDto): Address {
        return Address(
            id = dto.id,
            postalCode = dto.postalCode,
            country = dto.country,
            state = dto.state,
            city = dto.city,
            street = dto.street,
            buildingNumber = dto.buildingNumber
        )
    }
}