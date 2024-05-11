package com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer

import java.io.File

data class ManufacturerRequest(
    val name: String,
    val logoImage: File?,
)
