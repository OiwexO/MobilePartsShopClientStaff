package com.iwex.mobilepartsshopstaff.domain.model.user

data class Address(
    val id: Long,
    val postalCode: Int,
    val country: String,
    val state: String,
    val city: String,
    val street: String,
    val buildingNumber: String
)
