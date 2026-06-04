package com.hussam.hhsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hussam.hhsapp.R
import com.hussam.hhsapp.data.model.CartItem

class CartAdapter(private val cartItems: List<CartItem>) : 
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvCartItemName)
        val tvQuantity: TextView = view.findViewById(R.id.tvCartItemQuantity)
        val tvPrice: TextView = view.findViewById(R.id.tvCartItemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        holder.tvName.text = item.product.name
        holder.tvQuantity.text = "الكمية: ${item.quantity}"
        
        // حساب سعر القطعة مضروباً في الكمية المطلوبة
        val totalItemPrice = item.product.price * item.quantity
        holder.tvPrice.text = "$totalItemPrice YER"
    }

    override fun getItemCount(): Int = cartItems.size
}
