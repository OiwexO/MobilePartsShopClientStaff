package com.iwex.mobilepartsshopstaff.domain.entity.part

import java.io.File

data class PartRequest(
    val price: Double,
    val quantity: Int,
    val name: String,
    val deviceModels: List<String>,
    val specifications: String,
    val manufacturerId: Long,
    val deviceTypeId: Long,
    val partTypeId: Long,
    val image: File?,
)
