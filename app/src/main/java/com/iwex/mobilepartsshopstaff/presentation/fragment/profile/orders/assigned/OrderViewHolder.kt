package com.iwex.mobilepartsshopstaff.presentation.fragment.profile.orders.assigned

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iwex.mobilepartsshopstaff.R

class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val textViewOrderId: TextView = view.findViewById(R.id.textViewOrderId)

    val textViewOrderStatus: TextView = view.findViewById(R.id.textViewOrderStatus)

    val textViewOrderDate: TextView = view.findViewById(R.id.textViewOrderDate)

    val textViewOrderCustomerId: TextView = view.findViewById(R.id.textViewOrderCustomerId)

    val textViewOrderPrice: TextView = view.findViewById(R.id.textViewOrderPrice)

    val textViewOrderAddress: TextView = view.findViewById(R.id.textViewOrderAddress)

    val btnOpenOrder: Button = view.findViewById(R.id.btnOpenOrder)
}