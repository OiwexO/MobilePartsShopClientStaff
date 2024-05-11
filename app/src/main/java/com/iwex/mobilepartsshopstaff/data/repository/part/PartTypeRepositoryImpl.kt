package com.iwex.mobilepartsshopstaff.data.repository.part

import com.iwex.mobilepartsshopstaff.data.remote.MainApiService
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.part_type.PartTypeMapper
import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartTypeRequest
import com.iwex.mobilepartsshopstaff.domain.repository.part.PartTypeRepository
import javax.inject.Inject

class PartTypeRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: PartTypeMapper,
) : PartTypeRepository {

    override suspend fun getAllPartTypes(): Result<List<PartType>> {
        val response = try {
            apiService.getAllPartTypes()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = mapper.toEntityList(response)
        return Result.success(entities)
    }

    override suspend fun getPartType(id: Long): Result<PartType> {
        TODO("Not yet implemented")
    }

    override suspend fun createPartType(partTypeRequest: PartTypeRequest): Result<PartType> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePartType(
        id: Long,
        partTypeRequest: PartTypeRequest
    ): Result<PartType> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePartType(id: Long): Result<Unit> {
        TODO("Not yet implemented")
    }
}