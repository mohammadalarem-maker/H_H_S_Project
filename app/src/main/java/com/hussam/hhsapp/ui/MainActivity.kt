package com.hussam.hhsapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.hussam.hhsapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPhones = findViewById<Button>(R.id.btnPhones)
        val btnAccessories = findViewById<Button>(R.id.btnAccessories)
        val btnCosmetics = findViewById<Button>(R.id.btnCosmetics)
        val btnOpenCart = findViewById<Button>(R.id.btnOpenCart)

        btnPhones.setOnClickListener { openCategory("هواتف") }
        btnAccessories.setOnClickListener { openCategory("إكسسوارات") }
        btnCosmetics.setOnClickListener { openCategory("مستحضرات تجميل") }
        
        // فتح شاشة السلة عند الضغط على زر السلة
        btnOpenCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openCategory(categoryName: String) {
        val intent = Intent(this, ProductListActivity::class.java)
        intent.putExtra("CATEGORY_NAME", categoryName)
        startActivity(intent)
    }
}
