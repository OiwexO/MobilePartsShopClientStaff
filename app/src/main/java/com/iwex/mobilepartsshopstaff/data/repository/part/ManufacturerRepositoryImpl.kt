package com.iwex.mobilepartsshopstaff.data.repository.part

import com.iwex.mobilepartsshopstaff.data.remote.MainApiService
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.manufacturer.ManufacturerMapper
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.ManufacturerRequest
import com.iwex.mobilepartsshopstaff.domain.repository.part.ManufacturerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    /*override suspend fun createManufacturer(manufacturerRequest: ManufacturerRequest): Result<Manufacturer> {
        val requestDto = mapper.toRequestDto(manufacturerRequest)
        val response = try {
            apiService.createManufacturer(requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }*/
    override suspend fun createManufacturer(manufacturerRequest: ManufacturerRequest): Result<Manufacturer> {
        return withContext(Dispatchers.IO) {
            // Convert ManufacturerRequest to ManufacturerRequestDto
            val requestDto = mapper.toRequestDto(manufacturerRequest)

            try {
                // Execute API call asynchronously
                val response = apiService.createManufacturer(
                    name = requestDto.name,
                    logo = requestDto.logo
                ).execute()

                if (response.isSuccessful) {
                    // Convert response to entity
                    val entity = mapper.toEntity(response.body()!!)
                    Result.success(entity)
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    Result.failure(Exception("Failed to create manufacturer: $errorBody"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    //TODO rewrite updateManufacturer
    override suspend fun updateManufacturer(
        id: Long,
        manufacturerRequest: ManufacturerRequest
    ): Result<Manufacturer> {
        val requestDto = mapper.toRequestDto(manufacturerRequest)
        val response = try {
            apiService.updateManufacturer(id, requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun deleteManufacturer(id: Long): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.deleteManufacturer(id).execute()
                if (response.isSuccessful) {
                    Result.success(Unit)
                } else {
                    val errorMessage =
                        "Failed to delete manufacturer. HTTP code: ${response.code()}"
                    Result.failure(Exception(errorMessage))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}