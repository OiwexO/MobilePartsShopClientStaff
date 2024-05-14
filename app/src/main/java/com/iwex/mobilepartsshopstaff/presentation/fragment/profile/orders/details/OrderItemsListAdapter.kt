package com.iwex.mobilepartsshopstaff.presentation.fragment.profile.orders.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.order.OrderItem

class OrderItemsListAdapter : ListAdapter<OrderItem, OrderItemViewHolder>(OrderItemDiffCallback()) {

    var isUkrainianLocale = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_item, parent, false)
        return OrderItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        val item = currentList[position]
        val part = item.part
        val context = holder.itemView.context
        Glide.with(context)
            .load(part.imageUrl)
            .into(holder.imageViewPartImage)
        holder.textViewName.text = part.name
        holder.textViewSpecifications.text = part.specifications
        holder.textViewPartType.text =
            if (isUkrainianLocale) part.partType.nameUk else part.partType.nameEn
        holder.textViewManufacturerName.text = part.manufacturer.name
        holder.textViewDeviceType.text =
            if (isUkrainianLocale) part.deviceType.nameUk else part.deviceType.nameEn
        holder.textViewPrice.text =
            context.getString(R.string.price_placeholder, part.price.toString())
        holder.textViewQuantity.text =
            context.getString(R.string.quantity_placeholder, item.quantity)
    }
}