package com.example.demoapplication.utils.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import kotlin.concurrent.thread

val mainDispatcher = Dispatchers.Main
val defaultDispatcher = Dispatchers.Default
val unconfinedDispatcher = Dispatchers.Unconfined
val ioDispatcher = Dispatchers.IO


@OptIn(DelicateCoroutinesApi::class)
fun <T> ioCoroutineGlobal(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, block: suspend () -> T): Job =
    GlobalScope.launch(ioDispatcher, coroutineStart) {
        block()
    }


@OptIn(DelicateCoroutinesApi::class)
fun <T> mainCoroutineGlobal(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, block: suspend () -> T): Job =
    GlobalScope.launch(mainDispatcher, coroutineStart) {
        block()
    }

@OptIn(DelicateCoroutinesApi::class)
fun <T> defaultCoroutineGlobal(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, block: suspend () -> T): Job =
    GlobalScope.launch(defaultDispatcher, coroutineStart) {
        block()
    }

@OptIn(DelicateCoroutinesApi::class)
fun <T> unconfinedCoroutineGlobal(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, block: suspend () -> T): Job =
    GlobalScope.launch(unconfinedDispatcher, coroutineStart) {
        block()
    }

suspend fun <T> withMainContext(block: suspend () -> T): T =
    withContext(mainDispatcher) {
        block()
    }


suspend fun <T> withIOContext(block: suspend () -> T): T =
    withContext(ioDispatcher) {
        block()
    }


suspend fun <T> withDefaultContext(block: suspend () -> T): T =
    withContext(defaultDispatcher) {
        block()
    }


suspend fun <T> withUnconfinedContext(block: suspend () -> T): T =
    withContext(Dispatchers.Unconfined) {
        block()
    }


suspend fun <T> withNonCancellableContext(block: suspend () -> T): T =
    withContext(NonCancellable) {
        block()
    }


/**
 *
 * @receiver ViewModel
 * @param action SuspendFunction0<Unit>
 * @return Job
 */
fun ViewModel.viewModelIOCoroutine(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, action: suspend (scope: CoroutineScope) -> Unit = {}): Job =
    viewModelScope.launch(ioDispatcher, coroutineStart) {
        action(this)
    }


/**
 *
 * @receiver ViewModel
 * @param action SuspendFunction0<Unit>
 * @return Job
 */
fun ViewModel.viewModelMainCoroutine(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, action: suspend (scope: CoroutineScope) -> Unit = {}): Job =
    viewModelScope.launch(mainDispatcher, coroutineStart) {
        action(this)
    }


/**
 *
 * @receiver ViewModel
 * @param action SuspendFunction0<Unit>
 * @return Job
 */
fun ViewModel.viewModelDefaultCoroutine(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, action: suspend (scope: CoroutineScope) -> Unit = {}): Job =
    viewModelScope.launch(defaultDispatcher, coroutineStart) {
        action(this)
    }


/**
 *
 * @receiver ViewModel
 * @param action SuspendFunction0<Unit>
 * @return Job
 */
fun ViewModel.viewModelUnconfinedCoroutine(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, action: suspend (scope: CoroutineScope) -> Unit = {}): Job =
    viewModelScope.launch(unconfinedDispatcher, coroutineStart) {
        action(this)
    }


/**
 *
 * @receiver ViewModel
 * @param action SuspendFunction0<Unit>
 * @return Job
 */
fun ViewModel.viewModelNonCancellableCoroutine(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, action: suspend (scope: CoroutineScope) -> Unit = {}): Job =
    viewModelScope.launch(NonCancellable, coroutineStart) {
        action(this)
    }


inline fun CoroutineScope.main(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, crossinline function: suspend () -> Unit) {
    launch(mainDispatcher, coroutineStart) {
        function()
    }
}

inline fun CoroutineScope.io(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, crossinline function: suspend () -> Unit): Job =
    launch(ioDispatcher, coroutineStart) {
        function()
    }


inline fun CoroutineScope.default(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, crossinline function: suspend () -> Unit): Job =
    launch(defaultDispatcher, coroutineStart) {
        function()
    }


inline fun CoroutineScope.unconfined(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, crossinline function: suspend () -> Unit): Job =
    launch(unconfinedDispatcher, coroutineStart) {
        function()
    }


inline fun CoroutineScope.nonCancellable(coroutineStart: CoroutineStart = CoroutineStart.DEFAULT, crossinline function: suspend () -> Unit): Job =
    launch(NonCancellable, coroutineStart) {
        function()
    }


fun AppCompatActivity.ioCoroutine(action: suspend (scope: CoroutineScope) -> Unit = {}): Job = lifecycleScope.launch(ioDispatcher) {
    action(this)
}


fun AppCompatActivity.mainCoroutine(action: suspend (scope: CoroutineScope) -> Unit = {}): Job = lifecycleScope.launch(mainDispatcher) {
    action(this)
}


fun AppCompatActivity.unconfinedCoroutine(action: suspend (scope: CoroutineScope) -> Unit = {}): Job = lifecycleScope.launch(unconfinedDispatcher) {
    action(this)
}


fun AppCompatActivity.defaultCoroutine(action: suspend (scope: CoroutineScope) -> Unit = {}): Job = lifecycleScope.launch(defaultDispatcher) {
    action(this)
}


fun AppCompatActivity.nonCancellableCoroutine(action: suspend (scope: CoroutineScope) -> Unit = {}): Job = lifecycleScope.launch(NonCancellable) {
    action(this)
}


fun Fragment.ioCoroutine(action: suspend (scope: CoroutineScope) -> Unit = {}): Job = lifecycleScope.launch(ioDispatcher) {
    action(this)
}


fun Fragment.mainCoroutine(action: suspend (scope: CoroutineScope) -> Unit = {}): Job = lifecycleScope.launch(mainDispatcher) {
    action(this)
}


fun Fragment.unconfinedCoroutine(action: suspend (scope: CoroutineScope) -> Unit = {}): Job = lifecycleScope.launch(unconfinedDispatcher) {
    action(this)
}


fun Fragment.defaultCoroutine(action: suspend (scope: CoroutineScope) -> Unit = {}): Job = lifecycleScope.launch(defaultDispatcher) {
    action(this)
}


fun Fragment.nonCancellableCoroutine(action: suspend (scope: CoroutineScope) -> Unit = {}): Job = lifecycleScope.launch(NonCancellable) {
    action(this)
}