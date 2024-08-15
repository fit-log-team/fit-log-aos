package com.example.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MapOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MapInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MapRetrofit