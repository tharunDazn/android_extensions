package com.example.demoapplication.utils.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

val Activity.screenWidth: Int
    get() = Point().apply {
        windowManager.defaultDisplay.getSize(this)
    }.x

val Activity.screenHeight: Int
    get() = Point().apply {
        windowManager.defaultDisplay.getSize(this)
    }.y


/**
 * start activity by intent
 */
inline fun <reified T : Activity> Activity.startActivity() {
    val intent = Intent()
    intent.setClass(this, T::class.java)
    startActivity(intent)
}

/**
 * start activity by intent and finish calling activity
 */
private fun Activity.startActivity(cls: Class<*>, finishCallingActivity: Boolean = true) {
    val intent = Intent(this, cls)
    startActivity(intent)
    if (finishCallingActivity){
        finish()
    }
}

/**
 * add secure flag to prevents the content of the window from being captured or recorded by screen capture apps or screen recording features
 * call on onCreate method
 */
fun Activity.addSecureFlag() {
    window?.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
}

/**
 * remove secure flag to prevents the content of the window from being captured or recorded by screen capture apps or screen recording features
 * call on onDestroy method
 */
fun Activity.clearSecureFlag() {
    window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
}

/**
 * show back button in toolbar
 */
fun AppCompatActivity.showBackButton() {
    this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
}

/**
 * hide toolbar in activity
 */
fun AppCompatActivity.hideToolbar() {
    this.supportActionBar?.hide()
}

/**
 * show  toolbar in activity
 */
fun AppCompatActivity.showToolbar() {
    this.supportActionBar?.show()
}


fun AppCompatActivity.customToolbarDrawable(drawable: Drawable?) {
    supportActionBar?.setBackgroundDrawable(drawable)
}

fun AppCompatActivity.customIndicator(drawable: Drawable?) {
    supportActionBar?.setHomeAsUpIndicator(drawable)
}

fun AppCompatActivity.customIndicator(drawableResource: Int) {
    supportActionBar?.setHomeAsUpIndicator(drawableResource)
}



/**
 * Set Status Bar Color
 */
fun Activity.setStatusBarColor(@ColorInt color: Int) {
    window.statusBarColor = color
}

/**
 * Set Navigation Bar Color
 */
fun Activity.setNavigationBarColor(@ColorInt color: Int) {
    window.navigationBarColor = color
}

/**
 * Set Navigation Bar Divider Color if Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
 */
@RequiresApi(api = Build.VERSION_CODES.P)
fun Activity.setNavigationBarDividerColor(@ColorInt color: Int) {
    window.navigationBarDividerColor = color
}

/**
 *  Makes the activity enter fullscreen mode.
 */
@UiThread
fun Activity.enterFullScreenMode() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}


/**
 * Makes the Activity exit fullscreen mode.
 */
@UiThread
fun Activity.exitFullScreenMode() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
}

/**
 * Restarts an activity from itself with a fade animation
 * Keeps its existing extra bundles and has a intentBuilder to accept other parameters
 */
inline fun Activity.restart(intentBuilder: Intent.() -> Unit = {}) {
    val i = Intent(this, this::class.java)
    val oldExtras = intent.extras
    if (oldExtras != null)
        i.putExtras(oldExtras)
    i.intentBuilder()
    startActivity(i)
    finish()
}

/**
 * Force restart an entire application
 */

@SuppressLint("ScheduleExactAlarm")
fun Activity.restartApplication() {
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    val pending = PendingIntent.getActivity(this, 666, intent, PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    val alarm = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarm.setExactAndAllowWhileIdle(AlarmManager.RTC, System.currentTimeMillis() + 100, pending)
    finish()
    exitProcess(0)
}


inline fun <reified T> Activity.startActivityForResult(
    requestCode: Int,
    bundleBuilder: Bundle.() -> Unit = {},
    intentBuilder: Intent.() -> Unit = {},
) {
    val intent = Intent(this, T::class.java)
    intent.intentBuilder()
    val bundle = Bundle()
    bundle.bundleBuilder()
    startActivityForResult(intent, requestCode, if (bundle.isEmpty) null else bundle)
}


fun Activity.lockOrientation() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
}

fun Activity.lockCurrentScreenOrientation() {
    requestedOrientation = when (resources.configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        else -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
    }
}

fun Activity.unlockScreenOrientation() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
}

fun Activity.getBitmapFromUri(uri: Uri): Bitmap? {
    return contentResolver.openInputStream(uri)?.use {
        return@use BitmapFactory.decodeStream(it)
    }
}

fun AppCompatActivity.onSupportNavigateUpGoBack(): Boolean {
    onBackPressedDispatcher.onBackPressed()
    return true
}

fun Activity.longSnack(text: String) {
    currentFocus?.shortSnack(text) ?: run {
        val view: View = window.decorView.findViewById(android.R.id.content)
        view.longSnack(text)
    }
}

fun Activity.shortSnack(text: String) {
    currentFocus?.shortSnack(text) ?: run {
        val view: View = window.decorView.findViewById(android.R.id.content)
        view.shortSnack(text)
    }
}

fun Activity.snackBarWithAction(
    message: String,
    actionLabel: String,
    onClicked: () -> Unit
) {
    currentFocus?.snackBarWithAction(message, actionLabel, onClicked) ?: run {
        val view: View = window.decorView.findViewById(android.R.id.content)
        view.snackBarWithAction(message, actionLabel, onClicked)
    }
}


fun Activity.hideKeyboard() {
    currentFocus?.apply {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

fun Activity.showKeyboard() {
    Handler(Looper.getMainLooper()).postDelayed({
        currentFocus?.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }, 1000)
}





