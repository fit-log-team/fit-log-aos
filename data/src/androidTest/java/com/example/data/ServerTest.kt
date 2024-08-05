package com.example.data

import com.example.data.retrofit.ServerService
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(FooModule::class)
class ServerTest {

    @Inject
    lateinit var service: ServerService

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

//    @Inject
//    lateinit var foo: Foo

//    @BindValue
//    val foo: Foo = Foo("ServerTest")

    @Inject
    lateinit var bar: Bar

    @Test
    fun 주입테스트() {
        hiltRule.inject()
        Assert.assertNotNull(service)
    }
//
    @Test
    fun FOO_주입테스트() {
//        hiltRule.inject()
//        Assert.assertEquals("FakeFooModule", foo.tag)
    }

    @Test
    fun bar주입테스트() {
        hiltRule.inject()
        Assert.assertNotNull(bar)
        Assert.assertEquals("FakeFooModule", bar.foo.tag)

    }

}