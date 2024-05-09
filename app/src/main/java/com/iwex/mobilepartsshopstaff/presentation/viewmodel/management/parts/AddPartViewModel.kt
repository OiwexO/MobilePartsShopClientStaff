package com.iwex.mobilepartsshopstaff.presentation.viewmodel.management.parts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.part.PartRequest
import com.iwex.mobilepartsshopstaff.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshopstaff.domain.use_case.part.CreatePartUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.part.UpdatePartUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.part.device_type.GetAllDeviceTypesUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.part.manufacturer.GetAllManufacturersUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.part.part_type.GetAllPartTypesUseCase
import com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts.AddPartFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPartViewModel @Inject constructor(
    private val getAllDeviceTypesUseCase: GetAllDeviceTypesUseCase,
    private val getAllManufacturersUseCase: GetAllManufacturersUseCase,
    private val getAllPartTypesUseCase: GetAllPartTypesUseCase,
    private val createPartUseCase: CreatePartUseCase,
    private val updatePartUseCase: UpdatePartUseCase
) : ViewModel() {

    private val _deviceTypes = MutableLiveData<List<DeviceType>>()
    val deviceTypes: LiveData<List<DeviceType>> = _deviceTypes

    private val _manufacturers = MutableLiveData<List<Manufacturer>>()
    val manufacturers: LiveData<List<Manufacturer>> = _manufacturers

    private val _partTypes = MutableLiveData<List<PartType>>()
    val partTypes: LiveData<List<PartType>> = _partTypes

    private var _addPartFormState = MutableLiveData<AddPartFormState>()
    val addPartFormState: LiveData<AddPartFormState> = _addPartFormState

    private var _isSuccess = MutableLiveData(false)
    val isSuccess: LiveData<Boolean> = _isSuccess

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun loadData() {
        getAllDeviceTypes()
        getAllManufacturers()
        getAllPartTypes()
    }

    fun createPart(partRequest: PartRequest) {
        if (validatePartRequest(partRequest = partRequest, isNewPart = true)) {
            viewModelScope.launch {
                _isLoading.value = true
                val result = createPartUseCase(partRequest)
                result.onSuccess {
                    _isSuccess.value = true
                }.onFailure {
                    _errorMessage.value = it.message ?: "Create part failed"
                    Log.e(TAG, it.toString())
                }
            }
            _isLoading.value = false
        }
    }

    fun updatePart(partId: Long, partRequest: PartRequest) {
        if (validatePartRequest(partRequest = partRequest, isNewPart = false)) {
            viewModelScope.launch {
                _isLoading.value = true
                val result = updatePartUseCase(partId, partRequest)
                result.onSuccess {
                    _isSuccess.value = true
                }.onFailure {
                    _errorMessage.value = it.message ?: "Update part failed"
                    Log.e(TAG, it.toString())
                }
            }
            _isLoading.value = false
        }
    }

    private fun validatePartRequest(partRequest: PartRequest, isNewPart: Boolean): Boolean {
        val priceError = if (partRequest.price <= 0.0) R.string.invalid_price else null
        val quantityError = if (partRequest.quantity <= 0) R.string.invalid_quantity else null
        val nameError = if (partRequest.name.isEmpty()) R.string.invalid_part_name else null
        val specificationsError =
            if (partRequest.specifications.isEmpty()) R.string.invalid_specifications else null
        val manufacturerError =
            if (partRequest.manufacturerId <= 0) R.string.invalid_manufacturer else null
        val deviceTypeError =
            if (partRequest.deviceTypeId <= 0) R.string.invalid_device_type else null
        val partTypeError = if (partRequest.partTypeId <= 0) R.string.invalid_part_type else null
        val imageError =
            if (isNewPart && partRequest.image == null) R.string.invalid_part_image else null
        val isDataValid = priceError == null &&
                quantityError == null &&
                nameError == null &&
                specificationsError == null &&
                manufacturerError == null &&
                deviceTypeError == null &&
                partTypeError == null &&
                imageError == null
        val formState = AddPartFormState(
            nameError = nameError,
            priceError = priceError,
            quantityError = quantityError,
            specificationsError = specificationsError,
            manufacturerError = manufacturerError,
            deviceTypeError = deviceTypeError,
            partTypeError = partTypeError,
            imageError = imageError,
            isDataValid = isDataValid
        )
        _addPartFormState.value = formState
        return isDataValid
    }

    private fun getAllDeviceTypes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = getAllDeviceTypesUseCase()
                if (result.isSuccess) {
                    _deviceTypes.value =
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

    private fun getAllManufacturers() {
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

    private fun getAllPartTypes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = getAllPartTypesUseCase()
                if (result.isSuccess) {
                    _partTypes.value =
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

    companion object {
        private const val TAG = "AddManufacturerVM"
    }
}