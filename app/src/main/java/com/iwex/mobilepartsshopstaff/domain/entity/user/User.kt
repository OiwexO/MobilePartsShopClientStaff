package com.iwex.mobilepartsshopstaff.domain.entity.user

data class User(
    val id: Long,
    val firstname: String,
    val lastname: String,
    val userAuthority: UserAuthority,
)
