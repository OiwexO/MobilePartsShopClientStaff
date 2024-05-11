package com.iwex.mobilepartsshopstaff.presentation.fragment.management.manufacturers

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iwex.mobilepartsshopstaff.R

class ManufacturerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val imageViewManufacturerLogo: ImageView = view.findViewById(R.id.imageViewManufacturerLogo)

    val textViewManufacturerName: TextView = view.findViewById(R.id.textViewManufacturerName)

    val btnEditManufacturer: ImageButton = view.findViewById(R.id.btnEditManufacturer)

    val btnDeleteManufacturer: ImageButton = view.findViewById(R.id.btnDeleteManufacturer)
}