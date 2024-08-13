package com.example.data.di

import com.example.data.usecase.GetDirectionUseCaseImpl
import com.example.data.usecase.GetDummyUseCaseImpl
import com.example.domain.usecase.GetDirectionUseCase
import com.example.domain.usecase.GetDummyUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DirectionModule {
    @Binds
    abstract fun bindGetDirectionUseCase(uc: GetDirectionUseCaseImpl): GetDirectionUseCase

    @Binds
    abstract fun bindGetDummyUseCase(uc: GetDummyUseCaseImpl): GetDummyUseCase
}