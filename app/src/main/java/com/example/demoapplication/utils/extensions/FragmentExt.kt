package com.example.demoapplication.utils.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

fun Fragment.getNavController(@IdRes id: Int) = Navigation.findNavController(requireActivity(), id)

fun Fragment.shortToast(message: String) {
    requireContext().shortToast(message)
}

fun Fragment.longToast(message: String) {
    requireContext().longToast(message)
}

fun Fragment.hasPermission(permission: String): Boolean {
    return requireContext().hasPermission(permission)
}

fun Fragment.hasPermission(permission: Array<String>): Boolean {
    return requireContext().hasPermission(permission)
}

fun Fragment.hideKeyboard() {
    requireActivity().hideKeyboard()
}

fun Fragment.showKeyboard() {
    requireActivity().showKeyboard()
}

fun Fragment.longSnack(text: String) {
    view?.longSnack(text)
}

fun Fragment.shortSnack(text: String) {
    view?.shortSnack(text)
}

fun Fragment.snackBarWithAction(
    message: String,
    actionLabel: String,
    onClicked: () -> Unit
) {
    view?.snackBarWithAction(message, actionLabel, onClicked)
}
