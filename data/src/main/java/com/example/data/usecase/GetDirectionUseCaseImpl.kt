package com.example.data.usecase

import android.util.Log
import com.example.data.retrofit.DirectionService
import com.example.domain.model.DirectionResponse
import com.example.domain.usecase.GetDirectionUseCase
import javax.inject.Inject

class GetDirectionUseCaseImpl @Inject constructor(
    private val directionServiceAPI: DirectionService
): GetDirectionUseCase {
    override suspend fun invoke(): Result<DirectionResponse> = runCatching{
        Log.i("GetDirectionUseCaseImpl", "Invoke!")
        val response = directionServiceAPI.requestDirection(
            start = "126.941336,37.482408",
            goal = "126.924197,37.493457"
        )
        Log.i("GetDirectionUseCaseImpl", "response: $response")
        response
    }
}