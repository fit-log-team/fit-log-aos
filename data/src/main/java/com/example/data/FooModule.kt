package com.example.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class FooModule {
//    @Provides
//    fun provideFoo(): Foo {
//        return Foo("FooModule")
//    }
}

class Foo(val tag: String)

class Bar @Inject constructor(val foo: Foo)