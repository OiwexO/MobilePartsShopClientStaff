package com.iwex.mobilepartsshopstaff.data.repository.part

import com.iwex.mobilepartsshopstaff.data.remote.MainApiService
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.manufacturer.ManufacturerMapper
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.ManufacturerRequest
import com.iwex.mobilepartsshopstaff.domain.repository.part.ManufacturerRepository
import javax.inject.Inject

class ManufacturerRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: ManufacturerMapper
) : ManufacturerRepository {

    override suspend fun getAllManufacturers(): Result<List<Manufacturer>> {
        val response = try {
            apiService.getAllManufacturers()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = mapper.toEntityList(response)
        return Result.success(entities)
    }

    override suspend fun getManufacturer(id: Long): Result<Manufacturer> {
        val response = try {
            apiService.getManufacturer(id)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun createManufacturer(manufacturerRequest: ManufacturerRequest): Result<Manufacturer> {
        val requestDto = mapper.toRequestDto(manufacturerRequest)
        val response = try {
            apiService.createManufacturer(
                name = requestDto.name,
                logo = requestDto.logo
            )
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun updateManufacturer(
        id: Long,
        manufacturerRequest: ManufacturerRequest
    ): Result<Manufacturer> {
        val requestDto = mapper.toRequestDto(manufacturerRequest)
        val response = try {
            apiService.updateManufacturer(
                manufacturerId = id,
                name = requestDto.name,
                logo = requestDto.logo,
            )
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun deleteManufacturer(id: Long): Result<Unit> {
        return try {
            apiService.deleteManufacturer(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}