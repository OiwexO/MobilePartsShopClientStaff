package com.iwex.mobilepartsshopstaff.presentation.viewmodel.management.manufacturers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.ManufacturerRequest
import com.iwex.mobilepartsshopstaff.domain.use_case.part.manufacturer.CreateManufacturerUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.part.manufacturer.UpdateManufacturerUseCase
import com.iwex.mobilepartsshopstaff.presentation.fragment.management.manufacturers.AddManufacturerFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO rewrite error handling and form state check
@HiltViewModel
class AddManufacturerViewModel @Inject constructor(
    private val createManufacturerUseCase: CreateManufacturerUseCase,
    private val updateManufacturerUseCase: UpdateManufacturerUseCase,
) : ViewModel() {

    private val _addManufacturerFormState = MutableLiveData<AddManufacturerFormState>()
    val addManufacturerFormState: LiveData<AddManufacturerFormState> = _addManufacturerFormState

    private val _onSuccess = MutableLiveData<Unit>()
    val onSuccess: LiveData<Unit> = _onSuccess

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun createManufacturer(manufacturerRequest: ManufacturerRequest) {
        _isLoading.value = true
        if (validateManufacturerRequest(request = manufacturerRequest, isNewManufacturer = true)) {
            viewModelScope.launch {
                val result = createManufacturerUseCase(manufacturerRequest)
                result.onSuccess {
                    _onSuccess.value = Unit
                }.onFailure {
                    _errorMessage.value = it.message ?: "Create manufacturer failed"
                    Log.e(TAG, it.toString())
                }
            }
        }
        _isLoading.value = false
    }

    fun updateManufacturer(manufacturerId: Long, manufacturerRequest: ManufacturerRequest) {
        _isLoading.value = true
        if (validateManufacturerRequest(request = manufacturerRequest, isNewManufacturer = false)) {
            viewModelScope.launch {
                val result = updateManufacturerUseCase(manufacturerId, manufacturerRequest)
                result.onSuccess {
                    _onSuccess.value = Unit
                }.onFailure {
                    _errorMessage.value = it.message ?: "Update manufacturer failed"
                    Log.e(TAG, it.toString())
                }
            }
        }
        _isLoading.value = false
    }

    private fun validateManufacturerRequest(
        request: ManufacturerRequest,
        isNewManufacturer: Boolean
    ): Boolean {
        val nameError = if (request.name.isBlank()) R.string.invalid_manufacturer_name else null
        val logoError =
            if (isNewManufacturer && request.logoImage == null) R.string.invalid_manufacturer_logo else null
        val isDataValid = nameError == null && logoError == null
        val formState = AddManufacturerFormState(nameError, logoError, isDataValid)
        _addManufacturerFormState.value = formState
        return isDataValid
    }

    companion object {

        private const val TAG = "AddManufacturerVM"
    }
}