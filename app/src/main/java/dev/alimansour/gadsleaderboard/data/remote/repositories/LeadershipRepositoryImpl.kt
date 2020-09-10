package dev.alimansour.gadsleaderboard.data.remote.repositories


import dev.alimansour.gadsleaderboard.data.remote.network.LeadershipAPIs
import dev.alimansour.gadsleaderboard.data.remote.models.Learner
import dev.alimansour.gadsleaderboard.data.remote.models.ResultWrapper
import dev.alimansour.gadsleaderboard.data.remote.network.safeApiCall
import dev.alimansour.gadsleaderboard.domain.reposirories.LeadershipRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
class LeadershipRepositoryImpl(
    private val webService: LeadershipAPIs,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : LeadershipRepository {

    override suspend fun getSkillIqLeadership(): ResultWrapper<List<Learner>> {
        return safeApiCall(dispatcher) { webService.getSkillIQLeadershipAsync() }
    }

    override suspend fun getLearningLeadership(): ResultWrapper<List<Learner>> {
        return safeApiCall(dispatcher) { webService.getLearningLeadershipAsync() }
    }
}