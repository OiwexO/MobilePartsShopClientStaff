package com.iwex.mobilepartsshopstaff.data.remote.dto.mapper

abstract class ResponseMapper<Entity, ResponseDto> {

    abstract fun toEntity(dto: ResponseDto): Entity

    fun toEntityList(dtoList: List<ResponseDto>): List<Entity> {
        return dtoList.map { toEntity(it) }
    }
}