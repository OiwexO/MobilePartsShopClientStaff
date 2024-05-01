package com.iwex.mobilepartsshopstaff.domain.use_case.part

import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import com.iwex.mobilepartsshopstaff.domain.entity.part.PartRequest
import com.iwex.mobilepartsshopstaff.domain.repository.part.PartRepository
import javax.inject.Inject

class CreatePartUseCase @Inject constructor(
    private val partRepository: PartRepository
) {
    suspend operator fun invoke(partRequest: PartRequest): Result<Part> {
        return partRepository.createPart(partRequest)
    }
}