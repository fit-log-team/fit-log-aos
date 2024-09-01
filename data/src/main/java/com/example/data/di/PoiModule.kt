package com.example.data.di

import com.example.data.usecase.poi.GetPoiUseCaseImpl
import com.example.domain.usecase.GetPoiUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PoiModule {
    @Binds
    abstract fun bindPoiUseCase(uc: GetPoiUseCaseImpl): GetPoiUseCase
}