package com.hussam.hhsapp.ui.adapter
import com.hussam.hhsapp.data.model.Order
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hussam.hhsapp.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hussam.hhsapp.R

class OrderAdapter(private val orders: List<Order>) : 
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.tvOrderId)
        val tvUser: TextView = view.findViewById(R.id.tvOrderUser)
        val tvItems: TextView = view.findViewById(R.id.tvOrderItems)
        val tvPrice: TextView = view.findViewById(R.id.tvOrderPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.tvId.text = "طلب رقم: ...${order.orderId.takeLast(6)}"
        holder.tvUser.text = "صاحب الطلب: ${order.userEmail}"
        holder.tvPrice.text = "${order.totalPrice} YER"
        
        // تجميع المنتجات في نص واحد منسق وفصلها بفاصلة
        holder.tvItems.text = "البضائع: " + order.items.joinToString(" ، ")
    }

    override fun getItemCount(): Int = orders.size
}
