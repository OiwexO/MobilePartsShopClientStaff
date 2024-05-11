package com.iwex.mobilepartsshopstaff.domain.repository.part

import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.ManufacturerRequest

interface ManufacturerRepository {

    suspend fun getAllManufacturers(): Result<List<Manufacturer>>

    suspend fun getManufacturer(id: Long): Result<Manufacturer>

    suspend fun createManufacturer(manufacturerRequest: ManufacturerRequest): Result<Manufacturer>

    suspend fun updateManufacturer(id: Long, manufacturerRequest: ManufacturerRequest): Result<Manufacturer>

    suspend fun deleteManufacturer(id: Long): Result<Unit>
}