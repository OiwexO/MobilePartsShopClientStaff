package com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts

data class AddPartFormState(
    val nameError: Int? = null,
    val priceError: Int? = null,
    val quantityError: Int? = null,
    val deviceModelsError: Int? = null,
    val specificationsError: Int? = null,
    val manufacturerError: Int? = null,
    val deviceTypeError: Int? = null,
    val partTypeError: Int? = null,
    val imageError: Int? = null,
    val isDataValid: Boolean = false
)
