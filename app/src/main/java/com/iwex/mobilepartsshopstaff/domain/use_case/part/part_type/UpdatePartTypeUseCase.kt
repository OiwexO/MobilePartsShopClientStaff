package com.iwex.mobilepartsshopstaff.domain.use_case.part.part_type

import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartTypeRequest
import com.iwex.mobilepartsshopstaff.domain.repository.part.PartTypeRepository
import javax.inject.Inject

class UpdatePartTypeUseCase @Inject constructor(
    private val partTypeRepository: PartTypeRepository
) {
    suspend operator fun invoke(id: Long, partTypeRequest: PartTypeRequest): Result<PartType> {
        return partTypeRepository.updatePartType(id, partTypeRequest)
    }
}