package com.example.demoapplication.utils.extensions

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Patterns
import android.webkit.URLUtil
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.regex.Pattern

/**
 * get string or default value
 */
fun String?.orDefault(default: String): String {
    return this ?: default
}


/**
 * email validation pattern
 */
fun String.isValidEmail(): Boolean = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

/**
 * url validation
 */
fun String.isUrl(): Boolean {
    return URLUtil.isValidUrl(this)
}

/**
 * phone number validation
 */
fun String.isPhoneNumber(): Boolean {
    return android.util.Patterns.PHONE.matcher(this).matches()
}


fun String.validWithPattern(pattern: Pattern): Boolean {
    return pattern.matcher(lowercase(Locale.ROOT)).find()
}

fun String.validWithPattern(regex: String): Boolean {
    return Pattern.compile(regex).matcher(this).find()
}

fun String.removeWhitespaces(): String {
    return this.replace("[\\s-]*".toRegex(), "")
}

fun String.toIntOrZero() = if (this.toIntOrNull() == null) 0 else this.toInt()

fun String.isNumeric(): Boolean = this matches "-?\\d+(\\.\\d+)?".toRegex()

fun String.containsWebUrl() = Patterns.WEB_URL.matcher(this).find()

fun String?.nullToEmpty(): String = this ?: ""

fun String?.isNullOrZero() = this == "0" || this == null


/**
 * is string contain digit or alphanumeric characters
 */
val String.containsDigit: Boolean
    get() = matches(Regex(".*[0-9].*"))

val String.isAlphanumeric: Boolean
    get() = matches(Regex("[a-zA-Z0-9]*"))

/**
 * string to date converter
 */
@SuppressLint("SimpleDateFormat")
fun String.toDate(withFormat: String = "yyyy/MM/dd hh:mm"): Date {
    val dateFormat = SimpleDateFormat(withFormat)
    var convertedDate = Date()
    try {
        convertedDate = dateFormat.parse(this)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return convertedDate
}

/**
 * string to uri converter
 */
val String.asUri: Uri?
    get() = try {
        if (URLUtil.isValidUrl(this))
            Uri.parse(this)
        else
            null
    } catch (e: Exception) {
        null
    }

/** url */
fun String.toUri(): Uri {
    return Uri.parse(this)
}