package com.example.domain.usecase

import com.example.domain.model.DirectionResponse

interface GetDirectionUseCase {
    suspend operator fun invoke(): Result<DirectionResponse>
}