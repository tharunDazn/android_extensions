package com.example.demoapplication.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * EditText
 */

val EditText.value
    get() = text?.toString() ?: ""

fun EditText.isEmpty() = value.isEmpty()

fun EditText.onTextChanged(listener: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            listener(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}