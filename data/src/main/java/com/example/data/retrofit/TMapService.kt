package com.example.data.retrofit

import com.example.domain.model.workdirection.response.WorkDirection
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface TMapService {
    companion object {
        val BASE_URL = "https://apis.openapi.sk.com/tmap/routes/"
        val API_KEY = "quAq4XCd2n6JKJjRxGN9C2ipXgvDVwcd3F4A2aOS"
        val VERSION = "1"
        val CALLBACK = "function"
        val ACCEPT = "application/json"
        val CONTENT_TYPE = "application/json"
    }

    @POST("pedestrian")
    suspend fun requestWorkDirection(
        @Query("version") version: String = VERSION,
        @Query("callback") callback: String = CALLBACK,
        @Body requestBody: RequestBody
    ): WorkDirection
}