package com.example.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Inject

@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [FooModule::class]
//)
@InstallIn(SingletonComponent::class)
class FakeFooModule {

    @Provides
    fun provideFoo() :Foo {
        return Foo("FakeFooModule")
    }
}

class Bar @Inject constructor(val foo: Foo)