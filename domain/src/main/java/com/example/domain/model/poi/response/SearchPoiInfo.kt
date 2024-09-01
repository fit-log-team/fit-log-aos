package com.example.domain.model.poi.response

data class SearchPoiInfo(
    var totalCount: String,
    var count: String,
    var page: String,
    var pois: List<PoiItem>
)
