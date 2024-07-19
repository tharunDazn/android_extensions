package com.example.demoapplication.utils.extensions

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

/**
 * Visibility modifiers and check functions
 */
fun View.isVisibile(): Boolean = visibility == View.VISIBLE

fun View.isGone(): Boolean = visibility == View.GONE

fun View.isInvisible(): Boolean = visibility == View.INVISIBLE

fun View.makeVisible() { visibility = View.VISIBLE }

fun View.makeGone() { visibility = View.GONE }

fun View.makeInvisible() { visibility = View.INVISIBLE }

/**
 * show the view after block execution
 */
inline fun View.visibleIf(block: () -> Boolean) {
    if (visibility != View.VISIBLE && block()) {
        visibility = View.VISIBLE
    }
}

/**
 * hide the view after block execution
 */
inline fun View.invisibleIf(block: () -> Boolean) {
    if (visibility != View.INVISIBLE && block()) {
        visibility = View.INVISIBLE
    }
}

/**
 * get string from view
 */

fun View.getString(@StringRes resId: Int): String = resources.getString(resId)

fun View.getString(@StringRes resId: Int, vararg formatArgs: Any): String =
    resources.getString(resId, *formatArgs)

/**
* get dimen size from view
 */
fun View.getDimenSize(@DimenRes resId: Int): Int =
    resources.getDimensionPixelSize(resId)

/**
 * Open and Hides the soft input keyboard from the screen
 */
fun View.hideKeyboard(context: Context?) {
    val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.openKeyboard() {
    val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}


fun View.longSnack(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun View.shortSnack(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.snackBarWithAction(
    message: String,
    actionLabel: String,
    onClicked: () -> Unit
) {
    Snackbar.make(
        this, message, Snackbar.LENGTH_INDEFINITE
    ).setAction(actionLabel) {
        onClicked()
    }.show()
}

/**
 * inflate the view
 */
fun ViewGroup.inflate(@LayoutRes resId: Int): View =
    LayoutInflater.from(context).inflate(resId, this, false)
 