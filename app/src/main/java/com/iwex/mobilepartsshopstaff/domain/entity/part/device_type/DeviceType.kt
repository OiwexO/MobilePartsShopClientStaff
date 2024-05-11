package com.iwex.mobilepartsshopstaff.domain.entity.part.device_type

import java.io.Serializable

data class DeviceType(
    val id: Long,
    val nameEn: String,
    val nameUk: String,
) : Serializable
