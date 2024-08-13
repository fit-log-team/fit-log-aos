package com.example.data.di

import com.example.data.retrofit.DirectionService
import com.example.data.retrofit.ServerService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    //FIXME 타임아웃 설정 필요
    @Provides
    fun getOkHttpClient(interceptor: Interceptor, loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .addInterceptor(loggingInterceptor)
        .build()


    //FIXME 조회할 서버 url 필요
    @Provides
    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DirectionService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    //FIXME Retry 정책 수립 필요
    @Provides
    fun getInterceptor() = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("X-NCP-APIGW-API-KEY-ID", DirectionService.CLIENT_ID)
            .addHeader("X-NCP-APIGW-API-KEY", DirectionService.API_KEY)
            .build()
        chain.proceed(request)
    }

    @Provides
    fun getLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun getService(retrofit: Retrofit) = retrofit.create(DirectionService::class.java)
}