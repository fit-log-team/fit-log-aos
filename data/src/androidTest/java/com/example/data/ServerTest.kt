package com.example.data

import com.example.data.retrofit.DirectionService
import com.example.data.retrofit.ServerService
import com.example.domain.usecase.GetDirectionUseCase
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
    lateinit var service: DirectionService

    @Inject
    lateinit var getDirectionUseCase: GetDirectionUseCase

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun 주입테스트() {
        hiltRule.inject()
        Assert.assertNotNull(getDirectionUseCase)
    }

}