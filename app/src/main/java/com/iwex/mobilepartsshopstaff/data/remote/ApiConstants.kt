package com.iwex.mobilepartsshopstaff.data.remote

class ApiConstants {

    companion object {

        private const val GLOBAL_MAPPING_V1 = "/api/v1"

        const val ORDER_MAPPING_V1 = "$GLOBAL_MAPPING_V1/order"

        const val PART_MAPPING_V1 = "$GLOBAL_MAPPING_V1/part"

        const val USER_MAPPING_V1 = "$GLOBAL_MAPPING_V1/user"

        const val ADMIN_MAPPING_V1 = "$GLOBAL_MAPPING_V1/admin"

        const val STAFF_MAPPING_V1 = "$GLOBAL_MAPPING_V1/staff"
    }
}