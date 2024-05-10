package com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts.PartDiffCallback
import com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts.PartViewHolder

class PartsListAdapter : ListAdapter<Part, PartViewHolder>(PartDiffCallback()) {

    var onEditPartClickListener: ((Part) -> Unit)? = null

    var onDeletePartClickListener: ((Part) -> Unit)? = null

    var isUkrainianLocale = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_part, parent, false)
        return PartViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartViewHolder, position: Int) {
        val item = currentList[position]
        Glide.with(holder.itemView)
            .load(item.imageUrl)
            .into(holder.imageViewPartImage)
        holder.textViewName.text = item.name
        holder.textViewSpecifications.text = item.specifications
        holder.textViewPartType.text =
            if (isUkrainianLocale) item.partType.nameUk else item.partType.nameEn
        holder.textViewManufacturerName.text = item.manufacturer.name
        holder.textViewDeviceType.text =
            if (isUkrainianLocale) item.deviceType.nameUk else item.deviceType.nameEn
        holder.textViewPrice.text =
            holder.itemView.context.getString(R.string.price_placeholder, item.price)
        holder.btnEditPart.setOnClickListener {
            onEditPartClickListener?.invoke(item)
        }
        holder.btnDeletePart.setOnClickListener {
            onDeletePartClickListener?.invoke(item)
        }
    }
}