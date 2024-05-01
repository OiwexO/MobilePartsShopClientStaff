package com.iwex.mobilepartsshopstaff.domain.use_case.part.part_type

import com.iwex.mobilepartsshopstaff.domain.entity.part.PartType
import com.iwex.mobilepartsshopstaff.domain.entity.part.PartTypeRequest
import com.iwex.mobilepartsshopstaff.domain.repository.part.PartTypeRepository
import javax.inject.Inject

class CreatePartTypeUseCase @Inject constructor(
    private val partTypeRepository: PartTypeRepository
) {
    suspend operator fun invoke(partTypeRequest: PartTypeRequest): Result<PartType> {
        return partTypeRepository.createPartType(partTypeRequest)
    }
}