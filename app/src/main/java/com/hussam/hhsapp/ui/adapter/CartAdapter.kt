package com.hussam.hhsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hussam.hhsapp.R
import com.hussam.hhsapp.data.cart.CartManager
import com.hussam.hhsapp.data.model.CartItem

class CartAdapter(
    private var cartItems: List<CartItem>,
    private val onItemDeleted: () -> Unit // دالة إشعار لتحديث السعر الإجمالي بالشاشة الرئيسية
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvCartItemName)
        val tvQuantity: TextView = view.findViewById(R.id.tvCartItemQuantity)
        val tvPrice: TextView = view.findViewById(R.id.tvCartItemPrice)
        val tvDelete: TextView = view.findViewById(R.id.tvDeleteCartItem)
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
        
        val totalItemPrice = item.product.price * item.quantity
        holder.tvPrice.text = "$totalItemPrice YER"

        // حدث الضغط على زر الحذف
        holder.tvDelete.setOnClickListener {
            // مسح العنصر من المدير المحلي
            CartManager.removeProduct(item.product.id)
            // تحديث القائمة المحلية داخل المحول
            cartItems = CartManager.getCartItems()
            notifyDataSetChanged()
            // استدعاء دالة تحديث الحساب الإجمالي في الشاشة
            onItemDeleted()
        }
    }

    override fun getItemCount(): Int = cartItems.size
}
