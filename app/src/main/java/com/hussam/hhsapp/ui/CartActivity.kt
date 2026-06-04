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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val rvCartItems = findViewById<RecyclerView>(R.id.rvCartItems)
        val tvCartTotal = findViewById<TextView>(R.id.tvCartTotal)
        val btnCheckout = findViewById<Button>(R.id.btnCheckout)

        rvCartItems.layoutManager = LinearLayoutManager(this)
        
        val items = CartManager.getCartItems()
        val adapter = CartAdapter(items)
        rvCartItems.adapter = adapter
        
        tvCartTotal.text = "${CartManager.getTotalPrice()} YER"

        if (items.isEmpty()) {
            Toast.makeText(this, "السلة فارغة حالياً", Toast.LENGTH_SHORT).show()
        }

        btnCheckout.setOnClickListener {
            val currentItems = CartManager.getCartItems()
            if (currentItems.isEmpty()) {
                Toast.makeText(this, "لا يمكنك إرسال طلب وسلتك فارغة!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // تحويل المنتجات المضافة في السلة إلى نصوص واضحة (مثال: شاحن سريع x2)
            val textItemsList = currentItems.map { "${it.product.name} (الكمية: ${it.quantity})" }
            
            // تجهيز كائن الفاتورة والطلب
            val newOrder = Order(
                userEmail = FirebaseManager.getCurrentUserEmail(),
                items = textItemsList,
                totalPrice = CartManager.getTotalPrice(),
                timestamp = System.currentTimeMillis()
            )

            // إرسال الفاتورة سحابياً للفيربيز فوراً
            FirebaseManager.placeOrder(newOrder) { success ->
                if (success) {
                    Toast.makeText(this, "تم إرسال طلبك سحابياً لمتجر الحسام بنجاح! 🎉", Toast.LENGTH_LONG).show()
                    CartManager.clearCart()
                    tvCartTotal.text = "0.0 YER"
                    rvCartItems.adapter = CartAdapter(emptyList())
                    finish()
                } else {
                    Toast.makeText(this, "فشل إرسال الطلب، تحقق من الشبكة", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
