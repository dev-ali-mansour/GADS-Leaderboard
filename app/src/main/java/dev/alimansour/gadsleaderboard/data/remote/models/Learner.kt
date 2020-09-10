package dev.alimansour.gadsleaderboard.data.remote.models

import com.google.gson.annotations.SerializedName

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */data class Learner(
    val name: String,
    @SerializedName(value = "number", alternate = ["hours", "score"])
    val number: Int,
    val country: String,
    val badgeUrl: String
)