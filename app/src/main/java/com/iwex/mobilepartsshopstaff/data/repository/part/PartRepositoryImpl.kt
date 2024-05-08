package com.iwex.mobilepartsshopstaff.data.repository.part

import com.iwex.mobilepartsshopstaff.data.remote.MainApiService
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.PartMapper
import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import com.iwex.mobilepartsshopstaff.domain.entity.part.PartRequest
import com.iwex.mobilepartsshopstaff.domain.repository.part.PartRepository
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
        TODO("Not yet implemented")
    }

    override suspend fun createPart(partRequest: PartRequest): Result<Part> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePart(id: Long, partRequest: PartRequest): Result<Part> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePart(id: Long): Result<Unit> {
        TODO("Not yet implemented")
    }
}