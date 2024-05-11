package com.iwex.mobilepartsshopstaff.domain.entity.part

import com.iwex.mobilepartsshopstaff.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartType
import java.io.Serializable

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
) : Serializable
