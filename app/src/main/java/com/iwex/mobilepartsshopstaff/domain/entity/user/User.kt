package com.iwex.mobilepartsshopstaff.domain.entity.user

data class User(
    val id: Long,
    val username: String,
    val firstname: String,
    val lastname: String,
    val authority: UserAuthority,
)
