package com.iwex.mobilepartsshopstaff.domain.use_case.part

import com.iwex.mobilepartsshopstaff.domain.repository.part.PartRepository
import javax.inject.Inject

class DeletePartUseCase @Inject constructor(
    private val partRepository: PartRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return partRepository.deletePart(id)
    }
}
