package com.example.data.di

import com.example.data.usecase.workdirection.GetWorkDirectionUseCaseImpl
import com.example.domain.usecase.GetWorkDirectionUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DirectionModule {
    @Binds
    abstract fun bindGetWorkDirectionUsecase(uc: GetWorkDirectionUseCaseImpl): GetWorkDirectionUseCase
}