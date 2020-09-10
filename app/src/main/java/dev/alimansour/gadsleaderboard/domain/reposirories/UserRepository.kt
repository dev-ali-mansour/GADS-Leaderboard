package dev.alimansour.gadsleaderboard.domain.reposirories

import androidx.lifecycle.MutableLiveData
import dev.alimansour.gadsleaderboard.data.remote.models.ResultWrapper
import dev.alimansour.gadsleaderboard.data.remote.models.User

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
interface UserRepository {
    fun submit(user: User, resultWrapper: MutableLiveData<ResultWrapper<Any>>)
}