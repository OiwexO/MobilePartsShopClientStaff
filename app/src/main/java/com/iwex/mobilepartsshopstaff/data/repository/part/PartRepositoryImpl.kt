package com.iwex.mobilepartsshopstaff.data.repository.part

import com.iwex.mobilepartsshopstaff.data.remote.MainApiService
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.PartMapper
import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import com.iwex.mobilepartsshopstaff.domain.entity.part.PartRequest
import com.iwex.mobilepartsshopstaff.domain.repository.part.PartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PartRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: PartMapper
) : PartRepository {

    override suspend fun getAllParts(): Result<List<Part>> {
        val response = try {
            apiService.getAllParts()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = mapper.toEntityList(response)
        return Result.success(entities)
    }

    override suspend fun getPart(id: Long): Result<Part> {
        val response = try {
            apiService.getPart(id)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun createPart(partRequest: PartRequest): Result<Part> {
        val request = mapper.toRequestDto(partRequest)
        val response = try {
            apiService.createPart(
                price = request.price,
                quantity = request.quantity,
                name = request.name,
                deviceModels = request.deviceModels,
                specifications = request.specifications,
                manufacturerId = request.manufacturerId,
                deviceTypeId = request.deviceTypeId,
                partTypeId = request.partTypeId,
                image = request.partImage
            )
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun updatePart(id: Long, partRequest: PartRequest): Result<Part> {
        val request = mapper.toRequestDto(partRequest)
        val response = try {
            apiService.updatePart(
                partId = id,
                price = request.price,
                quantity = request.quantity,
                name = request.name,
                deviceModels = request.deviceModels,
                specifications = request.specifications,
                manufacturerId = request.manufacturerId,
                deviceTypeId = request.deviceTypeId,
                partTypeId = request.partTypeId,
                image = request.partImage
            )
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun deletePart(id: Long): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                apiService.deletePart(id)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}