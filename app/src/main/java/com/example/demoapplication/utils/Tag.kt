package com.example.demoapplication.utils

import android.os.Build
import android.util.Log

inline val <reified T> T.TAG: String
    get() {
        return if (!T::class.java.isAnonymousClass) {
            val name = T::class.java.simpleName
            if (name.length <= 23 || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) name else
                name.substring(0, 23)
        } else {
            val name = T::class.java.name
            if (name.length <= 23 || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                name else name.substring(name.length - 23, name.length)
        }
    }

inline fun <reified T> T.logv(message: String) = Log.v(TAG, message)
inline fun <reified T> T.logi(message: String) = Log.i(TAG, message)
inline fun <reified T> T.logw(message: String) = Log.w(TAG, message)
inline fun <reified T> T.logd(message: String) = Log.d(TAG, message)
inline fun <reified T> T.loge(message: String) = Log.e(TAG, message)