package com.iwex.mobilepartsshopstaff.domain.use_case.part.manufacturer

import com.iwex.mobilepartsshopstaff.domain.entity.part.Manufacturer
import com.iwex.mobilepartsshopstaff.domain.repository.part.ManufacturerRepository
import javax.inject.Inject

class GetManufacturerByIdUseCase @Inject constructor(
    private val manufacturerRepository: ManufacturerRepository
) {
    suspend operator fun invoke(id: Long): Result<Manufacturer> {
        return manufacturerRepository.getManufacturer(id)
    }
}