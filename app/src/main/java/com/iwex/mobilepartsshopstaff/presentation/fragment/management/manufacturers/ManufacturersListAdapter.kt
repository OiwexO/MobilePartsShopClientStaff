package com.iwex.mobilepartsshopstaff.presentation.fragment.management.manufacturers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer

class ManufacturersListAdapter : ListAdapter<Manufacturer, ManufacturerViewHolder>(ManufacturerDiffCallback()) {

    var onEditManufacturerClickListener: ((Manufacturer) -> Unit)? = null

    var onDeleteManufacturerClickListener: ((Manufacturer) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManufacturerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_manufacturer, parent, false)
        return ManufacturerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ManufacturerViewHolder, position: Int) {
        val item = currentList[position]
        Glide.with(holder.itemView)
            .load(item.logoUrl)
            .into(holder.imageViewManufacturerLogo)
        holder.textViewManufacturerName.text = item.name
        holder.btnEditManufacturer.setOnClickListener {
            onEditManufacturerClickListener?.invoke(item)
        }
        holder.btnDeleteManufacturer.setOnClickListener {
            onDeleteManufacturerClickListener?.invoke(item)
        }
    }
}