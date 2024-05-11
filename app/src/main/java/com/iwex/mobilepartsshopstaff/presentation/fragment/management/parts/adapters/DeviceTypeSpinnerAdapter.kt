package com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import com.iwex.mobilepartsshopstaff.domain.entity.part.device_type.DeviceType

class DeviceTypeSpinnerAdapter(
    context: Context,
    private val deviceTypes: List<DeviceType>,
    private val isUkrainianLocale: Boolean
) : ArrayAdapter<DeviceType>(context, 0, deviceTypes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val deviceType = getItem(position) ?: return view

        val text1 = view.findViewById<CheckedTextView>(android.R.id.text1)
        text1.text = if (isUkrainianLocale) deviceType.nameUk else deviceType.nameEn
        return view
    }
}