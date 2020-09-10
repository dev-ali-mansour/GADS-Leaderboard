package dev.alimansour.gadsleaderboard.data.remote.models

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
sealed class LeadershipTypes {
    object SkillIqLeaders : LeadershipTypes()
    object LearningLeaders : LeadershipTypes()
}