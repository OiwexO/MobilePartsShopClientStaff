package com.iwex.mobilepartsshopstaff.presentation.viewmodel.management.manufacturers

import com.iwex.mobilepartsshopstaff.domain.use_case.part.manufacturer.DeleteManufacturerUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.part.manufacturer.GetAllManufacturersUseCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageManufacturersViewModel @Inject constructor(
    private val getAllManufacturersUseCase: GetAllManufacturersUseCase,
    private val deleteManufacturerUseCase: DeleteManufacturerUseCase
) : ViewModel() {

    private val _manufacturers = MutableLiveData<List<Manufacturer>>()
    val manufacturers: LiveData<List<Manufacturer>> = _manufacturers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getAllManufacturers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = getAllManufacturersUseCase()
                if (result.isSuccess) {
                    _manufacturers.value =
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

    fun deleteManufacturerById(manufacturerId: Long) {
        viewModelScope.launch {
            try {
                val result = deleteManufacturerUseCase(manufacturerId)
                if (result.isSuccess) {
                    getAllManufacturers()
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Delete failed"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
}
