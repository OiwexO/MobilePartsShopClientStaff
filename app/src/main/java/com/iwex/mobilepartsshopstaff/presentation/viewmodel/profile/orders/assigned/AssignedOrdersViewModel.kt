package com.iwex.mobilepartsshopstaff.presentation.viewmodel.profile.orders.assigned

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iwex.mobilepartsshopstaff.domain.entity.order.Order
import com.iwex.mobilepartsshopstaff.domain.use_case.authentication.GetUserUseCase
import com.iwex.mobilepartsshopstaff.domain.use_case.staff.GetAssignedOrdersByStaffIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssignedOrdersViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getAssignedOrdersByStaffIdUseCase: GetAssignedOrdersByStaffIdUseCase,
) : ViewModel() {

    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>> = _orders

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getAssignedOrders() {
        _isLoading.value = true
        viewModelScope.launch {
            val userResult = getUserUseCase()
            userResult.onSuccess { user ->
                val staffId = user.id
                val result = getAssignedOrdersByStaffIdUseCase(staffId)
                result.onSuccess {
                    _orders.value = it
                }.onFailure {
                    Log.d(TAG, it.toString())
                    _errorMessage.value = it.message ?: "Get assigned orders failed"
                }
            }.onFailure {
                Log.d(TAG, it.toString())
                _errorMessage.value = it.message ?: "Get user failed"
            }
        }
        _isLoading.value = false
    }

    companion object {

        private const val TAG = "AssignedOrdersVm"
    }
}