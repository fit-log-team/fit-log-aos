package com.example.data.di

import com.example.data.retrofit.ServerService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    //FIXME 타임아웃 설정 필요
    @Provides
    fun getOkHttpClient(interceptor: Interceptor) = OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .build()


    //FIXME 조회할 서버 url 필요
    @Provides
    fun getRetrofit(client: OkHttpClient) = Retrofit
        .Builder()
        .baseUrl("url 작성")
        .client(client)
        .build()

    //FIXME Retry 정책 수립 필요
    @Provides
    fun getInterceptor() = Interceptor { chain ->
        val request = chain.request()
        chain.proceed(request)
    }

    @Provides
    fun getService(retrofit: Retrofit) = retrofit.create(ServerService::class.java)
}