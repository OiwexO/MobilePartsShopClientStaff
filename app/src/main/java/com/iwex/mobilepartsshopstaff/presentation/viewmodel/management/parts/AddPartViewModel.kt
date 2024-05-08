package com.iwex.mobilepartsshopstaff.presentation.viewmodel.management.parts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshopstaff.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshopstaff.domain.use_case.part.CreatePartUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.part.UpdatePartUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.part.device_type.GetAllDeviceTypesUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.part.manufacturer.GetAllManufacturersUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.part.part_type.GetAllPartTypesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPartViewModel @Inject constructor(
    private val getAllDeviceTypesUseCase: GetAllDeviceTypesUseCase,
    private val getAllManufacturersUseCase: GetAllManufacturersUseCase,
    private val getAllPartTypesUseCase: GetAllPartTypesUseCase,
//    private val createPartUseCase: CreatePartUseCase,
//    private val updatePartUseCase: UpdatePartUseCase
) : ViewModel() {

    private val _deviceTypes = MutableLiveData<List<DeviceType>>()
    val deviceTypes: LiveData<List<DeviceType>> = _deviceTypes

    private val _manufacturers = MutableLiveData<List<Manufacturer>>()
    val manufacturers: LiveData<List<Manufacturer>> = _manufacturers

    private val _partTypes = MutableLiveData<List<PartType>>()
    val partTypes: LiveData<List<PartType>> = _partTypes

    private var _isSuccess = MutableLiveData(false)
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun loadData() {
        getAllDeviceTypes()
        getAllManufacturers()
        getAllPartTypes()
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