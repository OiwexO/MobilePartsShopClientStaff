package com.iwex.mobilepartsshopstaff.data.remote.dto.mapper

abstract class ResponseRequestMapper<Entity, RequestEntity, ResponseDto, RequestDto> :
    ResponseMapper<Entity, ResponseDto>() {

    abstract fun toRequestDto(request: RequestEntity): RequestDto

}