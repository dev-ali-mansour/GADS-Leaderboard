package dev.alimansour.gadsleaderboard.data.remote.models

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
sealed class ResultWrapper<out T> {

    data class Success<out T>(val value: T?) : ResultWrapper<T>()

    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) :
        ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()
}