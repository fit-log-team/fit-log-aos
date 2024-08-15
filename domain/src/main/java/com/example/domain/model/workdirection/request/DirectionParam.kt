package com.example.domain.model.workdirection.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@Serializable
data class DirectionParam(
    var startName: String,
    var endName: String,
    var startX: Float,
    var endX: Float,
    var startY: Float,
    var endY: Float
) {
    fun toRequestBody(): RequestBody {
        return Json.encodeToString(this).toRequestBody()
    }
}
