package com.iwex.mobilepartsshopstaff.domain.entity.part

data class Part(
    val id: Long,
    val price: Double,
    val quantity: Int,
    val name: String,
    val deviceModels: List<String>,
    val specifications: String,
    val manufacturer: Manufacturer,
    val deviceType: DeviceType,
    val partType: PartType,
    val imageUrl: String,
)
