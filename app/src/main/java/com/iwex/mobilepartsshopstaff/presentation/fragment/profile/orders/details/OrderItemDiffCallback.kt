package com.iwex.mobilepartsshopstaff.presentation.fragment.profile.orders.details

import androidx.recyclerview.widget.DiffUtil
import com.iwex.mobilepartsshopstaff.domain.entity.order.OrderItem

class OrderItemDiffCallback : DiffUtil.ItemCallback<OrderItem>() {

    override fun areItemsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean {
        return oldItem == newItem
    }
}