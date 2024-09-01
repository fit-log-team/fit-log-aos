package com.example.domain.usecase

import com.example.domain.model.poi.response.PoiItem

interface GetPoiUseCase {
    suspend operator fun invoke(keyword: String): Result<List<PoiItem>>
}