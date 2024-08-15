package com.example.data.usecase.workdirection

import com.example.data.retrofit.TMapService
import com.example.domain.model.workdirection.request.DirectionParam
import com.example.domain.model.workdirection.response.Features
import com.example.domain.usecase.GetWorkDirectionUseCase
import javax.inject.Inject

class GetWorkDirectionUseCaseImpl @Inject constructor(
    private val tMapService: TMapService
): GetWorkDirectionUseCase{
    override suspend fun invoke(param: DirectionParam): Result<List<Features>> = runCatching{
        tMapService.requestWorkDirection(
            requestBody = param.toRequestBody()
        ).features
    }
}