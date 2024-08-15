package com.example.domain.usecase

import com.example.domain.model.workdirection.request.DirectionParam
import com.example.domain.model.workdirection.response.Features
import com.example.domain.model.workdirection.response.WorkDirection

interface GetWorkDirectionUseCase {
    suspend operator fun invoke(param: DirectionParam): Result<List<Features>>
}