package com.example.data.retrofit

import com.example.domain.model.DirectionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionService {
    companion object {
        val BASE_URL = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/"
        val API_KEY = "IYkvclLc0hBVnccZAXCOZzOKKQxWc1RQYAZ9QWWS"
        val CLIENT_ID = "61l86mwqvo"
    }

    @GET("driving")
    suspend fun requestDirection(
        @Query("start") start: String = "",
        @Query("goal") goal: String = "",
        @Query("waypoints") waypoints: String = "",
        @Query("option") option: String = "",
        @Query("cartype") cartype: String = "",
        @Query("fueltype") fueltype: String = "",
        @Query("mileage") mileage: String = "",
        @Query("lang") lang: String = "ko",
    ): DirectionResponse


}