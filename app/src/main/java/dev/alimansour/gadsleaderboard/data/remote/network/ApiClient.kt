package dev.alimansour.gadsleaderboard.data.remote.network

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dev.alimansour.gadsleaderboard.data.remote.models.ErrorResponse
import dev.alimansour.gadsleaderboard.data.remote.models.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
private const val BASE_URL = "https://gadsapi.herokuapp.com"
private const val GOOGLE_SHEET_URL = "https://docs.google.com"

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

val googleSheetRetrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(GOOGLE_SHEET_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}


private val interceptor = HttpLoggingInterceptor()

private val client by lazy {

    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .build()
}

/**
 * Call API in a safe way by handling expected exceptions that might occur during calling the API
 * @param dispatcher Coroutine Dispatcher
 * @param apiCall API call
 * @return ResultWrapper<T>
 */
suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> Deferred<T>
): ResultWrapper<T> {
    return withContext(dispatcher) {
        runCatching {
            ResultWrapper.Success(apiCall.invoke().await())
        }.getOrElse { throwable ->
            when (throwable) {
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                is IOException -> ResultWrapper.NetworkError
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}

/**
 * Parse HttpException to ErrorResponse
 * @param throwable HttpException
 * @return ErrorResponse
 */
fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return runCatching {

        throwable.response()?.errorBody()?.let {
            val gson = Gson()
            val error: ErrorResponse = gson.fromJson(
                it.string(),
                ErrorResponse::class.java
            )
            error
        }
    }.getOrElse {
        it.printStackTrace()
        null
    }
}