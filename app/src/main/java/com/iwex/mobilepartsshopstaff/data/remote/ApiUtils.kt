package com.iwex.mobilepartsshopstaff.data.remote

import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.BASE_URL
import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.MANUFACTURERS_MAPPING_V1
import com.iwex.mobilepartsshopstaff.data.remote.ApiConstants.Companion.PARTS_MAPPING_V1
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ApiUtils {

    companion object {

        fun getManufacturerLogoUrl(manufacturerId: Long): String {
            return "$BASE_URL/$MANUFACTURERS_MAPPING_V1/$manufacturerId/logo"
        }

        fun getPartImageUrl(partId: Long): String {
            return "$BASE_URL/$PARTS_MAPPING_V1/$partId/image"
        }

        fun manufacturerLogoToMultiPartBody(logo: File?) = toMultipartBody(
            logo,
            "logo",
            "image/png"
        )


        fun partImageToMultipartBody(partImage: File?) = toMultipartBody(
            partImage,
            "partImage",
            "image/jpeg"
        )

        private fun toMultipartBody(file: File?, name: String, mediaType: String): MultipartBody.Part {
            return if (file != null) {
                val requestFile = file.asRequestBody(mediaType.toMediaTypeOrNull())
                MultipartBody.Part.createFormData(name, file.name, requestFile)
            } else {
                val emptyRequestBody = "".toRequestBody("multipart/form-data".toMediaTypeOrNull())
                MultipartBody.Part.createFormData(name, "", emptyRequestBody)
            }
        }
    }
}