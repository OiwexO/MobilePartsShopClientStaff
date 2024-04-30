package com.iwex.mobilepartsshopstaff.domain.entity.part

import java.io.File

data class ManufacturerRequest(
    val name: String,
    val logoImage: File?,
)
