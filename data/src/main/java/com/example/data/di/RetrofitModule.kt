package com.example.data.di

import com.example.data.retrofit.TMapService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    @MapRetrofit
    fun provideMapRetrofit(@MapOkHttpClient client: OkHttpClient) = Retrofit
        .Builder()
        .baseUrl(TMapService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @MapInterceptor
    fun provideMapInterceptor() = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("appkey", TMapService.API_KEY)
            .addHeader("accept", TMapService.ACCEPT)
            .addHeader("content-type", TMapService.CONTENT_TYPE)
            .build()
        chain.proceed(request)
    }

    @Provides
    @MapOkHttpClient
    fun provideMapOkHttpClient(@MapInterceptor interceptor: Interceptor, loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun getLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideMapService(@MapRetrofit retrofit: Retrofit) = retrofit.create(TMapService::class.java)
}
