package com.github.example.data.pojo

/**
 * Created by kostadin.georgiev on 1/6/2020.
 */
sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: String? = null) :
        ResultWrapper<Nothing>()

    object Loading : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}