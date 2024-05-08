package com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iwex.mobilepartsshopstaff.R

class PartViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val imageViewPartImage: ImageView = view.findViewById(R.id.imageViewPartImage)

    val textViewName: TextView = view.findViewById(R.id.textViewName)

    val textViewSpecifications: TextView = view.findViewById(R.id.textViewSpecifications)

    val textViewPartType: TextView = view.findViewById(R.id.textViewPartType)

    val textViewManufacturerName: TextView = view.findViewById(R.id.textViewManufacturerName)

    val textViewDeviceType: TextView = view.findViewById(R.id.textViewDeviceType)

    val textViewPrice: TextView = view.findViewById(R.id.textViewPrice)

    val btnEditPart: ImageButton = view.findViewById(R.id.btnEditPart)

    val btnDeletePart: ImageButton = view.findViewById(R.id.btnDeletePart)
}
