package com.android.fetchandroidloganp.api

/**
 * Pretty common pattern, pulled this from Android
 */
sealed class APIResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : APIResult<T>(data = data)
    class Error<T>(message: String?, data: T? = null) : APIResult<T>(data, message)
    class Loading<T>() : APIResult<T>()
}