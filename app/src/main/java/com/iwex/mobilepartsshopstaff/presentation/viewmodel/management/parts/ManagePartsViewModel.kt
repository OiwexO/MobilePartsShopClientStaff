package com.iwex.mobilepartsshopstaff.presentation.viewmodel.management.parts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import com.iwex.mobilepartsshopstaff.domain.use_case.part.DeletePartUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.part.GetAllPartsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagePartsViewModel @Inject constructor(
    private val getAllPartsUseCase: GetAllPartsUseCase,
    private val deletePartUseCase: DeletePartUseCase,
) : ViewModel() {

    private val _parts = MutableLiveData<List<Part>>()
    val parts: LiveData<List<Part>> = _parts

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getAllParts() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getAllPartsUseCase()
            result.onSuccess {
                _parts.value = it
            }.onFailure {
                _errorMessage.value = it.message ?: "Get parts failed"
            }
        }
        _isLoading.value = false
    }

    fun deletePartById(partId: Long) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = deletePartUseCase(partId)
            result.onSuccess {
                getAllParts()
            }.onFailure {
                _errorMessage.value = it.message ?: "Delete part failed"
            }
        }
        _isLoading.value = false
    }
}