package com.iwex.mobilepartsshopstaff.domain.use_case.part.part_type

import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshopstaff.domain.repository.part.PartTypeRepository
import javax.inject.Inject

class GetPartTypeByIdUseCase @Inject constructor(
    private val partTypeRepository: PartTypeRepository
) {
    suspend operator fun invoke(id: Long): Result<PartType> {
        return partTypeRepository.getPartType(id)
    }
}