package com.example.domain.model.poi.response

data class NewAddressItem(
    var centerLat: String,
    var centerLon: String,
    var frontLat: String,
    var frontLon: String,
    var roadName: String,
    var bldNo1: String,
    var bldNo2: String,
    var roadId: String,
    var fullAddressRoad: String
)
