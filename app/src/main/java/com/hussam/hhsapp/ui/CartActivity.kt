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
import com.hussam.hhsapp.ui.adapter.CartAdapter

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val rvCartItems = findViewById<RecyclerView>(R.id.rvCartItems)
        val tvCartTotal = findViewById<TextView>(R.id.tvCartTotal)
        val btnCheckout = findViewById<Button>(R.id.btnCheckout)

        rvCartItems.layoutManager = LinearLayoutManager(this)
        
        // جلب عناصر السلة وتمريرها للمحول
        val items = CartManager.getCartItems()
        val adapter = CartAdapter(items)
        rvCartItems.adapter = adapter
        
        // تحديث المجموع الإجمالي للريال
        tvCartTotal.text = "${CartManager.getTotalPrice()} YER"

        if (items.isEmpty()) {
            Toast.makeText(this, "السلة فارغة حالياً", Toast.LENGTH_SHORT).show()
        }

        btnCheckout.setOnClickListener {
            if (CartManager.getCartItems().isEmpty()) {
                Toast.makeText(this, "لا يمكنك إرسال طلب وسلتك فارغة!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "تم إرسال طلبك بنجاح إلى متجر الحسام! جاري التجهيز...", Toast.LENGTH_LONG).show()
            CartManager.clearCart()
            tvCartTotal.text = "0.0 YER"
            
            // تحديث الواجهة بعد تفريغ السلة
            rvCartItems.adapter = CartAdapter(emptyList())
            finish()
        }
    }
}
