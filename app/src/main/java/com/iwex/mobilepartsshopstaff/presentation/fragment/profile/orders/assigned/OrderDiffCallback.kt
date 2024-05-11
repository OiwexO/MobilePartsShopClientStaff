package com.iwex.mobilepartsshopstaff.presentation.fragment.profile.orders.assigned

import androidx.recyclerview.widget.DiffUtil
import com.iwex.mobilepartsshopstaff.domain.entity.order.Order

class OrderDiffCallback : DiffUtil.ItemCallback<Order>() {

    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem == newItem
    }
}