package dev.alimansour.gadsleaderboard.domain.reposirories

import dev.alimansour.gadsleaderboard.data.remote.models.Learner
import dev.alimansour.gadsleaderboard.data.remote.models.ResultWrapper

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
interface LeadershipRepository {

    suspend fun getSkillIqLeadership(): ResultWrapper<List<Learner>>

    suspend fun getLearningLeadership(): ResultWrapper<List<Learner>>
}