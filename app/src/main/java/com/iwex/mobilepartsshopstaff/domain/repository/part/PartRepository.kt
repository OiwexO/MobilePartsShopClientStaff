package com.iwex.mobilepartsshopstaff.domain.repository.part

import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import com.iwex.mobilepartsshopstaff.domain.entity.part.PartRequest

interface PartRepository {

    suspend fun getAllParts(): Result<List<Part>>

    suspend fun getPart(id: Long): Result<Part>

    suspend fun createPart(partRequest: PartRequest): Result<Part>

    suspend fun updatePart(id: Long, partRequest: PartRequest): Result<Part>

    suspend fun deletePart(id: Long): Result<Unit>
}