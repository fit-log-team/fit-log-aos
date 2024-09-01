package com.example.data

import com.example.domain.usecase.GetPoiUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import retrofit2.http.GET
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidTest
class UseCasePoiTest {
    @Inject
    lateinit var getPoiUseCase: GetPoiUseCase

    @get:Rule
    val hiltMode = HiltAndroidRule(this)

    @Test
    fun Poi조회테스트() = runTest{
        hiltMode.inject()
        val data = getPoiUseCase.invoke("화곡역")
        Assert.assertNotNull(data)
    }
}