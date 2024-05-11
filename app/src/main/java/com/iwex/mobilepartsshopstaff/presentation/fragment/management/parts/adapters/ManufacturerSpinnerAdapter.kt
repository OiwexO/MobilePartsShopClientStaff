package com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts.adapters

import android.widget.ArrayAdapter
import com.iwex.mobilepartsshopstaff.domain.entity.part.manufacturer.Manufacturer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.iwex.mobilepartsshopstaff.R

class ManufacturerSpinnerAdapter(
    context: Context,
    private val manufacturers: List<Manufacturer>
) : ArrayAdapter<Manufacturer>(context, 0, manufacturers) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_item_manufacturer, parent, false)
        val manufacturer = getItem(position) ?: return view
        val imageViewManufacturerLogo = view.findViewById<ImageView>(R.id.imageViewManufacturerLogo)
        val textViewManufacturerName = view.findViewById<TextView>(R.id.textViewManufacturerName)
        Glide.with(view)
            .load(manufacturer.logoUrl)
            .into(imageViewManufacturerLogo)
        textViewManufacturerName.text = manufacturer.name
        return view
    }
}