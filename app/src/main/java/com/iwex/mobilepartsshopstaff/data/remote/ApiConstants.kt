package com.iwex.mobilepartsshopstaff.data.remote

class ApiConstants {

    companion object {

        private const val GLOBAL_MAPPING_V1 = "/api/v1"

        private const val PART_MAPPING_V1 = "$GLOBAL_MAPPING_V1/part"

        private const val STAFF_MAPPING_V1 = "$GLOBAL_MAPPING_V1/staff"

        const val BASE_URL = "http://192.168.0.112:8080"

        const val AUTHENTICATION_MAPPING_V1 = "$GLOBAL_MAPPING_V1/authentication/authorize/staff-admin"

        const val DEVICE_TYPES_MAPPING_V1 = "$PART_MAPPING_V1/device-types"

        const val MANUFACTURERS_MAPPING_V1 = "$PART_MAPPING_V1/manufacturers"

        const val PART_TYPES_MAPPING_V1 = "$PART_MAPPING_V1/part-types"

        const val PARTS_MAPPING_V1 = "$PART_MAPPING_V1/parts"

        const val STAFFS_MAPPING_V1 = "$STAFF_MAPPING_V1/staffs"

        const val ORDER_MAPPING_V1 = "$GLOBAL_MAPPING_V1/order"

        const val USER_MAPPING_V1 = "$GLOBAL_MAPPING_V1/user"

        const val ADMIN_MAPPING_V1 = "$GLOBAL_MAPPING_V1/admin"
    }
}