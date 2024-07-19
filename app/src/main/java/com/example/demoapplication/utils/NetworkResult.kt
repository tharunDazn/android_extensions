package com.example.demoapplication.utils

sealed class NetWorkResult<T>(val status: ApiStatus, val data: T?=null, val message: String?=null) {

    data class Success<T>(val successData: T?) : NetWorkResult<T>(status = ApiStatus.SUCCESS, data = successData, message = null)
    data class Error<T>(val exception: String) : NetWorkResult<T>(status = ApiStatus.ERROR, message = exception)
    data class Loading<T>(val isLoading: Boolean) : NetWorkResult<T>(status = ApiStatus.LOADING)

}

enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING
}