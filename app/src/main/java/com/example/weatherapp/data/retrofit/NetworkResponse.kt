package com.example.weatherapp.data.retrofit


// T refers to weather model
sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T): NetworkResponse<T>()
    data class Error(val message : String): NetworkResponse<Nothing>()
    object Loading: NetworkResponse<Nothing>()

}


//sealed class Resource<T>(val data: T? =null, val msg: String? = null) {
//
//    class Success<T>(data: T?): Resource<T>(data= data)
//    class Error<T>(msg: String): Resource<T>(msg = msg)
//    class Loading<T>: Resource<T>()
//}