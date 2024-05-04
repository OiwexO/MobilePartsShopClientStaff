package com.iwex.mobilepartsshopstaff.domain.repository.part

import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartTypeRequest

interface PartTypeRepository {

    suspend fun getAllPartTypes(): Result<List<PartType>>

    suspend fun getPartType(id: Long): Result<PartType>

    suspend fun createPartType(partTypeRequest: PartTypeRequest): Result<PartType>

    suspend fun updatePartType(id: Long, partTypeRequest: PartTypeRequest): Result<PartType>

    suspend fun deletePartType(id: Long): Result<Unit>
}