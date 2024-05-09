package com.iwex.mobilepartsshopstaff.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshopstaff.domain.use_case.authentication.CheckIsUserAuthenticatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkIsUserAuthenticatedUseCase: CheckIsUserAuthenticatedUseCase
) : ViewModel() {

    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean> = _isUserAuthenticated

    fun checkUserAuthentication() {
        viewModelScope.launch {
            try {
                val result = checkIsUserAuthenticatedUseCase()
                _isUserAuthenticated.value = result.getOrThrow()
            } catch (e: Exception) {
                _isUserAuthenticated.value = false
            }
        }
    }
}