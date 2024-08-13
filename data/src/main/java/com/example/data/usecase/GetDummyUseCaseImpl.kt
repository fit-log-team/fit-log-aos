package com.example.data.usecase

import com.example.domain.usecase.GetDummyUseCase
import timber.log.Timber
import javax.inject.Inject

class GetDummyUseCaseImpl @Inject constructor(

): GetDummyUseCase {
    override fun invoke() {
        Timber.i("GOGOGOGOGO")
    }
}