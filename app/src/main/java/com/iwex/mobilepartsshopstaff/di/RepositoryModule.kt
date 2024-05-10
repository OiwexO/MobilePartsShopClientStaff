package com.iwex.mobilepartsshopstaff.di

import android.content.Context
import android.content.SharedPreferences
import com.iwex.mobilepartsshopstaff.data.remote.AuthenticationApiService
import com.iwex.mobilepartsshopstaff.data.remote.MainApiService
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.authentication.AuthenticationMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.order.OrderMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.PartMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.device_type.DeviceTypeMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.manufacturer.ManufacturerMapper
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.part_type.PartTypeMapper
import com.iwex.mobilepartsshopstaff.data.repository.authentication.AuthenticationRepositoryImpl
import com.iwex.mobilepartsshopstaff.data.repository.part.DeviceTypeRepositoryImpl
import com.iwex.mobilepartsshopstaff.data.repository.part.ManufacturerRepositoryImpl
import com.iwex.mobilepartsshopstaff.data.repository.part.PartRepositoryImpl
import com.iwex.mobilepartsshopstaff.data.repository.part.PartTypeRepositoryImpl
import com.iwex.mobilepartsshopstaff.data.repository.staff.StaffRepositoryImpl
import com.iwex.mobilepartsshopstaff.domain.repository.authentication.AuthenticationRepository
import com.iwex.mobilepartsshopstaff.domain.repository.part.DeviceTypeRepository
import com.iwex.mobilepartsshopstaff.domain.repository.part.ManufacturerRepository
import com.iwex.mobilepartsshopstaff.domain.repository.part.PartRepository
import com.iwex.mobilepartsshopstaff.domain.repository.part.PartTypeRepository
import com.iwex.mobilepartsshopstaff.domain.repository.staff.StaffRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    private const val SHARED_PREFS_NAME = "jwt_prefs"

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =
        appContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun provideAuthenticationRepository(
        apiService: AuthenticationApiService,
        preferences: SharedPreferences,
        mapper: AuthenticationMapper
    ): AuthenticationRepository = AuthenticationRepositoryImpl(apiService, preferences, mapper)

    @[Provides Singleton]
    fun provideDeviceTypeRepository(
        apiService: MainApiService,
        mapper: DeviceTypeMapper
    ): DeviceTypeRepository = DeviceTypeRepositoryImpl(apiService, mapper)

    @[Provides Singleton]
    fun provideManufacturerRepository(
        apiService: MainApiService,
        mapper: ManufacturerMapper
    ): ManufacturerRepository = ManufacturerRepositoryImpl(apiService, mapper)

    @[Provides Singleton]
    fun providePartRepository(
        apiService: MainApiService,
        mapper: PartMapper
    ): PartRepository = PartRepositoryImpl(apiService, mapper)

    @[Provides Singleton]
    fun providePartTypeRepository(
        apiService: MainApiService,
        mapper: PartTypeMapper
    ): PartTypeRepository = PartTypeRepositoryImpl(apiService, mapper)

    @[Provides Singleton]
    fun provideStaffRepository(
        apiService: MainApiService,
        mapper: OrderMapper
    ): StaffRepository = StaffRepositoryImpl(apiService, mapper)
}