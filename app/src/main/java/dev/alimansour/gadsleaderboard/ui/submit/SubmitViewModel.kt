package dev.alimansour.gadsleaderboard.ui.submit

import android.app.Application
import android.util.Patterns
import android.webkit.URLUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.alimansour.gadsleaderboard.R
import dev.alimansour.gadsleaderboard.data.remote.models.ResultWrapper
import dev.alimansour.gadsleaderboard.data.remote.models.User
import dev.alimansour.gadsleaderboard.data.remote.network.submitAPI
import dev.alimansour.gadsleaderboard.data.remote.repositories.UserRepositoryImpl
import dev.alimansour.gadsleaderboard.domain.usecases.UserUseCases
import kotlinx.coroutines.launch

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
class SubmitViewModel(application: Application) : AndroidViewModel(application) {

    private val _user: MutableLiveData<User> = MutableLiveData(User())
    val user: LiveData<User> = _user

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg

    private val _showConfirmationDialog: MutableLiveData<Boolean> = MutableLiveData(false)
    val showConfirmationDialog: LiveData<Boolean> = _showConfirmationDialog

    private val _showSuccessDialog: MutableLiveData<Boolean> = MutableLiveData(false)
    val showSuccessDialog: LiveData<Boolean> = _showSuccessDialog

    private val _showFailDialog: MutableLiveData<Boolean> = MutableLiveData(false)
    val showFailDialog: LiveData<Boolean> = _showFailDialog

    private val _submitStatus: MutableLiveData<ResultWrapper<Any>> = MutableLiveData()
    val submitStatus: LiveData<ResultWrapper<Any>> = _submitStatus

    private val repository = UserRepositoryImpl(submitAPI)
    private val useCases = UserUseCases(repository)

    /**
     * Submit project
     */
    fun submit() {
        if (validateInputs()) {
            if (_showConfirmationDialog.value!!) {
                viewModelScope.launch {
                    useCases.submit(user.value!!, _submitStatus)
                }
            } else {
                _showConfirmationDialog.value = true
            }
        }
    }

    /**
     * Set visibility for confirmation dialog
     * @param show Show dialog
     */
    fun setConfirmationDialog(show: Boolean) {
        _showConfirmationDialog.value = show
    }

    /**
     * Set visibility for fail dialog
     * @param show Show dialog
     */
    fun setFailDialog(show: Boolean) {
        _showFailDialog.value = show
    }

    /**
     * Set visibility for success dialog
     * @param show Show dialog
     */
    fun setSuccessDialog(show: Boolean) {
        _showSuccessDialog.value = show
    }

    /**
     * Validate user inputs
     * @return Are user inputs valid
     */
    private fun validateInputs(): Boolean {
        val currentUser = user.value
        val validInputs = currentUser != null &&
                currentUser.firstName.isNotBlank() &&
                currentUser.lastName.isNotBlank() &&
                currentUser.email.isNotBlank() &&
                currentUser.projectLink.isNotBlank()

        if (!validInputs) {
            _errorMsg.value =
                getApplication<Application>().resources.getString(R.string.all_fields_required)
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(currentUser!!.email).matches()) {
            _errorMsg.value =
                getApplication<Application>().resources.getString(R.string.invalid_email)

            return false
        }

        if (!URLUtil.isValidUrl(currentUser.projectLink)) {
            _errorMsg.value =
                getApplication<Application>().resources.getString(R.string.invalid_link)

            return false
        }

        return true
    }
}