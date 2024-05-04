package com.iwex.mobilepartsshopstaff.domain.use_case.part.manufacturer

import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.ManufacturerRequest
import com.iwex.mobilepartsshopstaff.domain.repository.part.ManufacturerRepository
import javax.inject.Inject

class CreateManufacturerUseCase @Inject constructor(
    private val manufacturerRepository: ManufacturerRepository
) {
    suspend operator fun invoke(manufacturerRequest: ManufacturerRequest): Result<Manufacturer> {
        return manufacturerRepository.createManufacturer(manufacturerRequest)
    }
}