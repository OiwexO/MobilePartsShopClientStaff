package com.iwex.mobilepartsshopstaff.di

import android.content.Context
import android.content.SharedPreferences
import com.iwex.mobilepartsshopstaff.data.remote.MainApiService
import com.iwex.mobilepartsshopstaff.data.remote.dto.mapper.part.manufacturer.ManufacturerMapper
import com.iwex.mobilepartsshopstaff.data.repository.authentication.JwtTokenRepositoryImpl
import com.iwex.mobilepartsshopstaff.data.repository.part.ManufacturerRepositoryImpl
import com.iwex.mobilepartsshopstaff.domain.repository.authentication.JwtTokenRepository
import com.iwex.mobilepartsshopstaff.domain.repository.part.ManufacturerRepository
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
    fun provideJwtTokenRepository(preferences: SharedPreferences): JwtTokenRepository =
        JwtTokenRepositoryImpl(preferences)

    @[Provides Singleton]
    fun provideManufacturerRepository(
        apiService: MainApiService,
        mapper: ManufacturerMapper
    ): ManufacturerRepository = ManufacturerRepositoryImpl(apiService, mapper)

}