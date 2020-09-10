package dev.alimansour.gadsleaderboard.ui.leadershipboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.alimansour.gadsleaderboard.data.remote.network.leadershipAPIs
import dev.alimansour.gadsleaderboard.data.remote.models.Learner
import dev.alimansour.gadsleaderboard.data.remote.models.ResultWrapper
import dev.alimansour.gadsleaderboard.data.remote.repositories.LeadershipRepositoryImpl
import dev.alimansour.gadsleaderboard.domain.usecases.LeadershipUsecases
import kotlinx.coroutines.launch

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
class PageViewModel : ViewModel() {

    private val _skillIqLeadershipList: MutableLiveData<ResultWrapper<List<Learner>>> =
        MutableLiveData()
    private val _learningLeadershipList: MutableLiveData<ResultWrapper<List<Learner>>> =
        MutableLiveData()

    val skillIqLeadershipList: LiveData<ResultWrapper<List<Learner>>> = _skillIqLeadershipList
    val learningLeadershipList: LiveData<ResultWrapper<List<Learner>>> = _learningLeadershipList

    private val repository = LeadershipRepositoryImpl(leadershipAPIs)
    private val useCases = LeadershipUsecases(repository)

    init {
        loadLeaders()
    }

    fun loadLeaders() {
        viewModelScope.launch {
            _skillIqLeadershipList.postValue(useCases.getSkillIqLeadership())
            _learningLeadershipList.postValue(useCases.getLearningLeadership())
        }
    }
}