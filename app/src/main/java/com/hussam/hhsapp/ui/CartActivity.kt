package com.hussam.hhsapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hussam.hhsapp.R
import com.hussam.hhsapp.data.cart.CartManager
import com.hussam.hhsapp.data.model.Order
import com.hussam.hhsapp.firebase.FirebaseManager
import com.hussam.hhsapp.ui.adapter.CartAdapter

class CartActivity : AppCompatActivity() {

    private lateinit var tvCartTotal: TextView
    private lateinit var rvCartItems: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        rvCartItems = findViewById(R.id.rvCartItems)
        tvCartTotal = findViewById<TextView>(R.id.tvCartTotal)
        val btnCheckout = findViewById<Button>(R.id.btnCheckout)

        rvCartItems.layoutManager = LinearLayoutManager(this)
        
        // إنشاء المحول وتمرير دالة تحديث السعر الإجمالي عند كل عملية حذف
        val items = CartManager.getCartItems()
        val adapter = CartAdapter(items) {
            updateTotalPrice()
        }
        rvCartItems.adapter = adapter
        
        updateTotalPrice()

        if (items.isEmpty()) {
            Toast.makeText(this, "السلة فارغة حالياً", Toast.LENGTH_SHORT).show()
        }

        btnCheckout.setOnClickListener {
            val currentItems = CartManager.getCartItems()
            if (currentItems.isEmpty()) {
                Toast.makeText(this, "لا يمكنك إرسال طلب وسلتك فارغة!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textItemsList = currentItems.map { "${it.product.name} (الكمية: ${it.quantity})" }
            
            val newOrder = Order(
                userEmail = FirebaseManager.getCurrentUserEmail(),
                items = textItemsList,
                totalPrice = CartManager.getTotalPrice(),
                timestamp = System.currentTimeMillis()
            )

            FirebaseManager.placeOrder(newOrder) { success ->
                if (success) {
                    Toast.makeText(this, "تم إرسال طلبك سحابياً لمتجر الحسام بنجاح! 🎉", Toast.LENGTH_LONG).show()
                    CartManager.clearCart()
                    updateTotalPrice()
                    rvCartItems.adapter = CartAdapter(emptyList()) {}
                    finish()
                } else {
                    Toast.makeText(this, "فشل إرسال الطلب، تحقق من الشبكة", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // دالة لتحديث نص السعر الإجمالي بالريال
    private fun updateTotalPrice() {
        tvCartTotal.text = "${CartManager.getTotalPrice()} YER"
    }
}
