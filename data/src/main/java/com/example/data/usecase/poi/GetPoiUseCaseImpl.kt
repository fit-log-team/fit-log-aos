package com.example.data.usecase.poi

import com.example.data.retrofit.TMapService
import com.example.domain.model.poi.response.PoiItem
import com.example.domain.usecase.GetPoiUseCase
import javax.inject.Inject

class GetPoiUseCaseImpl @Inject constructor(
    var tMapService: TMapService
): GetPoiUseCase {
    override suspend fun invoke(keyword: String): Result<List<PoiItem>> = runCatching{
        val response = tMapService.requestPoi(searchKeyword = keyword).searchPoiInfo.pois.poi
        response
    }.onFailure {
        throw Exception(it)
    }
}