package com.example.presentation.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import com.example.presentation.model.IntentData
import kotlin.reflect.KClass

object UiController {
    fun addActivity(context: Context?, targetActivity: KClass<out Activity>, data: IntentData? = null) {
        if (context == null) return
        Intent(context, targetActivity.java).apply {
            data?.let {
                putExtra(it)
            }
        }.let {
            val options = ActivityOptionsCompat.makeCustomAnimation(
                context,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            context.startActivity(it, options.toBundle())
        }
    }
}