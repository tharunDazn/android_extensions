package com.example.demoapplication.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream

@Suppress("Unused")
class AppPrefs(context: Context) {

    companion object {
        const val PREF_NAME_KEY = "secure_prefs_name"
        const val IS_LOGIN_KEY = "is_login"
        const val IS_FIRST_LOGIN_KEY = "is_first_login"
        const val FIRST_TIME_LAUNCH_KEY = "first_time_launch"
        const val AVATAR_KEY = "avatar"
    }

    private val masterKey =
        MasterKey.Builder(context.applicationContext)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

    val sharedPreferences = EncryptedSharedPreferences.create(
        context.applicationContext,
        PREF_NAME_KEY,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun putIsLogin(value: Boolean) {
        sharedPreferences.edit {
            putBoolean(IS_LOGIN_KEY, value)
        }
    }

    fun isLogin(): Boolean = sharedPreferences.getBoolean(IS_LOGIN_KEY, false)

    fun putFirstTimeLaunch(value: Boolean) {
        sharedPreferences.edit {
            putBoolean(FIRST_TIME_LAUNCH_KEY, value)
        }
    }

    fun isFirstTimeLaunch(): Boolean = sharedPreferences.getBoolean(FIRST_TIME_LAUNCH_KEY, true)

    fun isFirstLogin(): Boolean = sharedPreferences.getBoolean(IS_FIRST_LOGIN_KEY, true)

    fun putIsFirstLogin(value: Boolean) {
        sharedPreferences.edit {
            putBoolean(IS_FIRST_LOGIN_KEY, value)
        }
    }

    fun putString(key: String, text: String) {
        sharedPreferences.edit {
            putString(key, text)
        }
    }

    fun getString(key: String): String = sharedPreferences.getString(key, "").toString()

    fun putInt(key: String, value: Int) {
        sharedPreferences.edit {
            putInt(key, value)
        }
    }

    fun getInt(key: String): Int = sharedPreferences.getInt(key, 0)

    fun clear() {
        sharedPreferences.edit {
            clear()
        }
    }

    fun <T> put(`object`: T, key: String) {
        val jsonString = Gson().toJson(`object`)
        sharedPreferences.edit {
            putString(key, jsonString)
        }
    }

    inline fun <reified T> get(key: String): T? {
        val value = sharedPreferences.getString(key, null)
        return Gson().fromJson(value, T::class.java)
    }

    fun <T> putList(`object`: ArrayList<T>, key: String) {
        val jsonString = Gson().toJson(`object`)
        sharedPreferences.edit {
            putString(key, jsonString)
        }
    }

    inline fun <reified T> getList(key: String): ArrayList<T>? {
        val type = object : TypeToken<ArrayList<T>>() {}.type
        return Gson().fromJson(
            sharedPreferences.getString(key, null), type
        )
    }

    fun putInitialNameAvatar(bitmap: Bitmap) {
        try {
            ByteArrayOutputStream().apply {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, this)
                val imageBytes = this.toByteArray()
                val imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)

                sharedPreferences.edit(commit = true) {
                    putString(AVATAR_KEY, imageString)
                }
            }
        } catch (e: Exception) {
            Log.e("saveInitialNameAvatar","saveInitialNameAvatar: ${e.localizedMessage}")
            e.printStackTrace()
        }
    }

    fun getInitialNameAvatar(): Bitmap? {
        try {
            val imageString = sharedPreferences.getString(AVATAR_KEY, "") ?: ""
            if (imageString.isEmpty()) return null
            val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        } catch (e: Exception) {
            Log.e("loadInitialNameAvatar","loadInitialNameAvatar: ${e.localizedMessage}")
            e.printStackTrace()
        }
        return null
    }
}