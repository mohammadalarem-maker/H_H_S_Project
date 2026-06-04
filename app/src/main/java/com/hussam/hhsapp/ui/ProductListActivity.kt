package com.hussam.hhsapp.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hussam.hhsapp.R
import com.hussam.hhsapp.firebase.FirebaseManager
import com.hussam.hhsapp.ui.adapter.ProductAdapter

class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val tvCategoryTitle = findViewById<TextView>(R.id.tvCategoryTitle)
        val rvProducts = findViewById<RecyclerView>(R.id.rvProducts)
        
        val categoryName = intent.getStringExtra("CATEGORY_NAME") ?: "المنتجات"
        tvCategoryTitle.text = categoryName

        rvProducts.layoutManager = LinearLayoutManager(this)

        // جلب المنتجات وربطها بالمحول لعرضها على الشاشة
        FirebaseManager.getProductsByCategory(categoryName) { products ->
            if (products.isEmpty()) {
                Toast.makeText(this, "لا توجد منتجات متوفرة حالياً في هذا القسم", Toast.LENGTH_SHORT).show()
            } else {
                val adapter = ProductAdapter(products)
                rvProducts.adapter = adapter
            }
        }
    }
}
