package com.astro.yourchannel.util

sealed class YtResource<T> (

    val data : T? = null,
    val message : String? = null
){

    class Success<T>(data : T):YtResource<T>(data)
    class Error<T>(message: String?,data: T? = null) : YtResource<T>(data, message)
    class Loading<T> : YtResource<T>()

}