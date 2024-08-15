package com.example.presentation.util

import android.content.Intent
import android.os.Build
import com.example.presentation.model.IntentData

fun Intent.putExtra(data: IntentData) {
    data.map.keys.forEach {
        if (data.map[it] is String) {
            putExtra(it, data.map[it] as String)
        } else {
            putExtra(it, data.map[it] as java.io.Serializable)
        }
    }
}

fun <T: java.io.Serializable> Intent.intentSerializable(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, clazz)
    } else {
        this.getSerializableExtra(key) as T?
    }
}