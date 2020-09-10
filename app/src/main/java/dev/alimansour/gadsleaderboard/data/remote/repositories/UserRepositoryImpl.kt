package dev.alimansour.gadsleaderboard.data.remote.repositories

import androidx.lifecycle.MutableLiveData
import dev.alimansour.gadsleaderboard.data.remote.models.ResultWrapper
import dev.alimansour.gadsleaderboard.data.remote.models.User
import dev.alimansour.gadsleaderboard.data.remote.network.SubmitAPIs
import dev.alimansour.gadsleaderboard.domain.reposirories.UserRepository
import retrofit2.Call
import retrofit2.Response

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
class UserRepositoryImpl(
    private val webService: SubmitAPIs
) : UserRepository {

    override fun submit(user: User, resultWrapper: MutableLiveData<ResultWrapper<Any>>) {

        webService.submit(
            user.email, user.firstName, user.lastName, user.projectLink
        ).enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful)
                    resultWrapper.postValue(ResultWrapper.Success(null))
                else
                    resultWrapper.postValue(ResultWrapper.GenericError(response.code(), null))
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                resultWrapper.postValue(ResultWrapper.NetworkError)
            }
        })
    }
}