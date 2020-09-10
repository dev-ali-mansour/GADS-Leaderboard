package dev.alimansour.gadsleaderboard.data.remote.network

import dev.alimansour.gadsleaderboard.data.remote.models.Learner
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */

val leadershipAPIs: LeadershipAPIs by lazy {
    retrofit.create(LeadershipAPIs::class.java)
}

interface LeadershipAPIs {

    @GET("/api/skilliq")
    fun getSkillIQLeadershipAsync(): Deferred<List<Learner>>


    @GET("/api/hours")
    fun getLearningLeadershipAsync(): Deferred<List<Learner>>
}