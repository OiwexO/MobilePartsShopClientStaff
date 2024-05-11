package com.iwex.mobilepartsshopstaff.domain.use_case.part.part_type

import com.iwex.mobilepartsshopstaff.domain.repository.part.PartTypeRepository
import javax.inject.Inject

class DeletePartTypeUseCase @Inject constructor(
    private val partTypeRepository: PartTypeRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return partTypeRepository.deletePartType(id)
    }
}
