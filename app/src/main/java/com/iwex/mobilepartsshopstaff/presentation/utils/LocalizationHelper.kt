package com.iwex.mobilepartsshopstaff.presentation.utils

import android.content.res.Resources

object LocalizationHelper {

    fun isUkrainianLocale(resources: Resources): Boolean {
        val currentLocale = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            resources.configuration.locales[0]
        } else {
            @Suppress("DEPRECATION")
            resources.configuration.locale
        }
        return currentLocale.language == "uk"
    }

    fun getLocalizedString(resources: Resources, stringEn: String, stringUk: String): String {
        return if (isUkrainianLocale(resources)) {
            stringUk
        } else {
            stringEn
        }
    }

}