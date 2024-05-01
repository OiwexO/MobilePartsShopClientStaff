package com.iwex.mobilepartsshopstaff.domain.use_case.part

import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import com.iwex.mobilepartsshopstaff.domain.repository.part.PartRepository
import javax.inject.Inject

class GetAllPartsUseCase @Inject constructor(
    private val partRepository: PartRepository
) {
    suspend operator fun invoke(): Result<List<Part>> {
        return partRepository.getAllParts()
    }
}