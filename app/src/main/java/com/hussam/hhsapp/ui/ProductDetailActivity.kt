package com.hussam.hhsapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hussam.hhsapp.R
import com.hussam.hhsapp.data.model.Product
import com.hussam.hhsapp.data.cart.CartManager

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val ivImage = findViewById<ImageView>(R.id.ivDetailImage)
        val tvName = findViewById<TextView>(R.id.tvDetailName)
        val tvPrice = findViewById<TextView>(R.id.tvDetailPrice)
        val tvDesc = findViewById<TextView>(R.id.tvDetailDescription)
        val btnAddToCart = findViewById<Button>(R.id.btnAddToCart)

        // استقبال بيانات المنتج
        val id = intent.getStringExtra("PROD_ID") ?: ""
        val name = intent.getStringExtra("PROD_NAME") ?: "منتج غير معروف"
        val desc = intent.getStringExtra("PROD_DESC") ?: ""
        val price = intent.getDoubleExtra("PROD_PRICE", 0.0)

        // إعادة بناء كائن المنتج المحلي لحفظه في السلة
        val currentProduct = Product(id = id, name = name, description = desc, price = price)

        tvName.text = name
        tvDesc.text = desc
        tvPrice.text = "$price YER"
        ivImage.setImageResource(R.mipmap.ic_launcher)

        btnAddToCart.setOnClickListener {
            // حفظ المنتج في مدير السلة الحقيقي
            CartManager.addProduct(currentProduct)
            Toast.makeText(this, "تمت إضافة $name إلى السلة! الإجمالي الحالي: ${CartManager.getTotalPrice()} YER", Toast.LENGTH_LONG).show()
        }
    }
}
