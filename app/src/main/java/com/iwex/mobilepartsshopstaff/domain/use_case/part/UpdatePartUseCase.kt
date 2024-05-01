package com.iwex.mobilepartsshopstaff.domain.use_case.part

import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import com.iwex.mobilepartsshopstaff.domain.entity.part.PartRequest
import com.iwex.mobilepartsshopstaff.domain.repository.part.PartRepository
import javax.inject.Inject

class UpdatePartUseCase @Inject constructor(
    private val partRepository: PartRepository
) {
    suspend operator fun invoke(id: Long, partRequest: PartRequest): Result<Part> {
        return partRepository.updatePart(id, partRequest)
    }
}