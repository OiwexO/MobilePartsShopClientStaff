package com.iwex.mobilepartsshopstaff.presentation.viewmodel.management.manufacturers

import android.util.Log
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
        _isLoading.value = true
        viewModelScope.launch {
            val result = getAllManufacturersUseCase()
            result.onSuccess {
                _manufacturers.value = it
            }.onFailure {
                Log.d(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get manufacturers failed"
            }
        }
        _isLoading.value = false
    }

    fun deleteManufacturerById(manufacturerId: Long) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = deleteManufacturerUseCase(manufacturerId)
            result.onSuccess {
                getAllManufacturers()
            }.onFailure {
                _errorMessage.value = it.message ?: "Delete part failed"
            }
        }
        _isLoading.value = false
    }

    companion object {

        private const val TAG = "ManageManufacturersVm"
    }
}
