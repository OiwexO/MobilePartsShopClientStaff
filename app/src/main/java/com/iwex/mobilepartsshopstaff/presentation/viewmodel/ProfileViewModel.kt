package com.iwex.mobilepartsshopstaff.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshopstaff.domain.entity.user.User
import com.iwex.mobilepartsshopstaff.domain.use_case.authentication.GetUserUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.authentication.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _onLogout = MutableLiveData<Unit>()
    val onLogout: LiveData<Unit> = _onLogout

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getUser() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getUserUseCase()
            result.onSuccess {
                _user.value = it
            }.onFailure {
                _errorMessage.value = it.message ?: "Get user failed"
            }
        }
        _isLoading.value = false
    }

    fun logout() {
        viewModelScope.launch {
            val result = logoutUseCase()
            result.onSuccess {
                _onLogout.value = Unit
            }.onFailure {
                _errorMessage.value = it.message ?: "Logout failed"
            }
        }
    }
}