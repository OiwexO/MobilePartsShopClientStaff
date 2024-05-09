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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

//TODO rewrite error handling and form state check
@HiltViewModel
class AddManufacturerViewModel @Inject constructor(
    private val createManufacturerUseCase: CreateManufacturerUseCase,
    private val updateManufacturerUseCase: UpdateManufacturerUseCase,
) : ViewModel() {

    private var _isSuccess = MutableLiveData(false)
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    private var _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _errorMessage = MutableLiveData<Int>()
    val errorMessage: LiveData<Int>
        get() = _errorMessage

    fun createManufacturer(name: String, logoImage: File?) {
        if (name.isBlank()) {
            _errorMessage.value = R.string.error_enter_name
            return
        }
        if (logoImage == null) {
            _errorMessage.value = R.string.error_select_logo
            return
        }
        val manufacturerRequest = ManufacturerRequest(name, logoImage)
        viewModelScope.launch {
            _isLoading.value = true
            val result = createManufacturerUseCase(manufacturerRequest)
            result.onSuccess {
                _isSuccess.value = true
            }.onFailure {
                Log.e(TAG, it.toString())
            }
        }
        _isLoading.value = false
    }

    fun updateManufacturer(manufacturerId: Long, name: String, logoImage: File?) {
        if (name.isBlank()) {
            _errorMessage.value = R.string.error_enter_name
            return
        }
        val manufacturerRequest = ManufacturerRequest(name, logoImage)
        viewModelScope.launch {
            _isLoading.value = true
            val result = updateManufacturerUseCase(manufacturerId, manufacturerRequest)
            result.onSuccess {
                _isSuccess.value = true
            }.onFailure {
                Log.e(TAG, it.toString())
            }
        }
        _isLoading.value = false
    }

    companion object {
        private const val TAG = "AddManufacturerVM"
    }
}