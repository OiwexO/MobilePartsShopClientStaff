package com.iwex.mobilepartsshopstaff.presentation.viewmodel.profile.orders.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshopstaff.domain.entity.order.Order
import com.iwex.mobilepartsshopstaff.domain.use_case.staff.GetOrderByIdUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.staff.UpdateOrderStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    private val getOrderByIdUseCase: GetOrderByIdUseCase,
    private val updateOrderStatusUseCase: UpdateOrderStatusUseCase,
) : ViewModel() {

    private val _order = MutableLiveData<Order>()
    val order: LiveData<Order> = _order

    private val _onSuccess = MutableLiveData<Unit>()
    val onSuccess: LiveData<Unit> = _onSuccess

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getOrder(orderId: Long) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getOrderByIdUseCase(orderId)
            result.onSuccess {
                _order.value = it
            }.onFailure {
                Log.d(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get order failed"
            }
        }
        _isLoading.value = false
    }

    fun updateOrderStatus(orderId: Long) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = updateOrderStatusUseCase(orderId)
            result.onSuccess {
                _onSuccess.value = Unit
            }.onFailure {
                Log.d(TAG, it.toString())
                _errorMessage.value = it.message ?: "Update order status failed"
            }
        }
        _isLoading.value = false
    }

    companion object {

        private const val TAG = "OrderDetailsVm"
    }
}