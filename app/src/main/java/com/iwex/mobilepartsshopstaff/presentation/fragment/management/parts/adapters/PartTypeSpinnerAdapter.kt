package com.iwex.mobilepartsshopstaff.presentation.fragment.management.parts.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import com.iwex.mobilepartsshopstaff.domain.entity.part.part_type.PartType

class PartTypeSpinnerAdapter(
    context: Context,
    private val partTypes: List<PartType>,
    private val isUkrainianLocale: Boolean
) : ArrayAdapter<PartType>(context, 0, partTypes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val partType = getItem(position) ?: return view

        val text1 = view.findViewById<CheckedTextView>(android.R.id.text1)
        text1.text = if (isUkrainianLocale) partType.nameUk else partType.nameEn
        return view
    }
}