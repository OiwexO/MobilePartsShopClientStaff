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
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = getAllPartsUseCase()
                if (result.isSuccess) {
                    _parts.value =
                        result.getOrThrow()
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Unknown error"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
            _isLoading.value = false
        }
    }

    fun deletePartById(partId: Long) {
        viewModelScope.launch {
            try {
                val result = deletePartUseCase(partId)
                if (result.isSuccess) {
                    getAllParts()
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Delete failed"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
}