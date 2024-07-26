package com.test.moviesapp.data.network

import com.google.gson.Gson
import com.test.moviesapp.domain.GeneralErrorResponse
import com.test.moviesapp.domain.InvalidExceptionGeneral
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {

    protected suspend fun <T> getResult(
        call: suspend () -> Response<T>,
        forceError: Boolean = false
    ): T {
        try {
            Timber.e("remoteDataSource")
            if (forceError) {
                throw Exception("Force error for testing purpose only")
            }
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return body
            }

            if (response.code() in 400..499) {
                val errorResponse = Gson().fromJson(
                    response.errorBody()?.string() ?: "",
                    GeneralErrorResponse::class.java
                )
                Timber.tag("BaseDataSource").e("Error 400 -- %s", errorResponse.error.message)
                throw InvalidExceptionGeneral(errorResponse.error.message ?: "Error 400")
            }
            throw Exception("Not e ${response.code()} ${response.body()}")
        } catch (e: Throwable) {
            error(e.message ?: "")
        }
    }
}

