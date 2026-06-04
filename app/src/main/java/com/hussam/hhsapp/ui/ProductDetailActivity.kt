package com.hussam.hhsapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hussam.hhsapp.R

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val ivImage = findViewById<ImageView>(R.id.ivDetailImage)
        val tvName = findViewById<TextView>(R.id.tvDetailName)
        val tvPrice = findViewById<TextView>(R.id.tvDetailPrice)
        val tvDesc = findViewById<TextView>(R.id.tvDetailDescription)
        val btnAddToCart = findViewById<Button>(R.id.btnAddToCart)

        // استقبال البيانات الممررة من قائمة المنتجات
        val name = intent.getStringExtra("PROD_NAME") ?: "منتج غير معروف"
        val desc = intent.getStringExtra("PROD_DESC") ?: ""
        val price = intent.getDoubleExtra("PROD_PRICE", 0.0)

        // عرض البيانات داخل الواجهة
        tvName.text = name
        tvDesc.text = desc
        tvPrice.text = "$price YER"
        ivImage.setImageResource(R.mipmap.ic_launcher) // افتراضية حالياً

        btnAddToCart.setOnClickListener {
            Toast.makeText(this, "تمت إضافة $name إلى السلة بنجاح!", Toast.LENGTH_SHORT).show()
            // هنا سنربطها لاحقاً بقاعدة بيانات السلة المحلية أو السحابية للطلب
        }
    }
}
