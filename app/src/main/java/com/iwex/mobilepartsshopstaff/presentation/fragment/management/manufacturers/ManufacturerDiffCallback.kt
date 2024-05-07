package com.iwex.mobilepartsshopstaff.presentation.fragment.management.manufacturers

import androidx.recyclerview.widget.DiffUtil
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer

class ManufacturerDiffCallback : DiffUtil.ItemCallback<Manufacturer>() {

    override fun areItemsTheSame(oldItem: Manufacturer, newItem: Manufacturer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Manufacturer, newItem: Manufacturer): Boolean {
        return oldItem == newItem
    }
}