package com.example.data

import com.example.domain.usecase.GetWorkDirectionUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class UseCaseDependencyTest {

    @Inject
    lateinit var getDirectionUseCase: GetWorkDirectionUseCase

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun 의존성주입테스트() {
        hiltRule.inject()
        Assert.assertNotNull(getDirectionUseCase)
    }

}