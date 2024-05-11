package com.iwex.mobilepartsshopstaff.presentation.fragment.profile.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.ListAdapter
import com.iwex.mobilepartsshopstaff.R
import com.iwex.mobilepartsshopstaff.domain.entity.order.Order
import com.iwex.mobilepartsshopstaff.domain.entity.order.OrderStatus
import java.text.SimpleDateFormat
import java.util.Locale

class OrdersListAdapter : ListAdapter<Order, OrderViewHolder>(OrderDiffCallback()) {

    private val dateFormat = SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.getDefault())

    var onOpenOrderClickListener: ((Order) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = currentList[position]
        val context = holder.itemView.context
        holder.textViewOrderId.text = context.getString(R.string.order_id_placeholder, item.id)
        @StringRes val status = when (item.status) {
            OrderStatus.PENDING -> R.string.order_status_pending
            OrderStatus.PROCESSING -> R.string.order_status_processing
            OrderStatus.SHIPPING -> R.string.order_status_shipping
            OrderStatus.DELIVERED -> R.string.order_status_delivered
            OrderStatus.CANCELED -> R.string.order_status_canceled
        }
        val statusStr = context.getString(status)
        holder.textViewOrderStatus.text =
            context.getString(R.string.order_status_placeholder, statusStr)
        val dateStr = dateFormat.format(item.date)
        holder.textViewOrderDate.text = context.getString(R.string.order_date_placeholder, dateStr)
        holder.textViewOrderCustomerId.text =
            context.getString(R.string.order_customer_id_placeholder, item.customerId)
        holder.textViewOrderPrice.text =
            context.getString(R.string.order_price_placeholder, item.price.toString())
        holder.textViewOrderAddress.text =
            context.getString(R.string.order_address_placeholder, item.shippingAddress.city)
        holder.btnOpenOrder.setOnClickListener {
            onOpenOrderClickListener?.invoke(item)
        }
    }
}