package com.hussam.hhsapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hussam.hhsapp.R
import com.hussam.hhsapp.data.model.Product
import com.hussam.hhsapp.firebase.FirebaseManager

class AddProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val etName = findViewById<EditText>(R.id.etProdName)
        val etDesc = findViewById<EditText>(R.id.etProdDescription)
        val etPrice = findViewById<EditText>(R.id.etProdPrice)
        val etCategory = findViewById<EditText>(R.id.etProdCategory)
        val etStock = findViewById<EditText>(R.id.etProdStock)
        val btnAdd = findViewById<Button>(R.id.btnAddProduct)

        btnAdd.setOnClickListener {
            val name = etName.text.toString().trim()
            val desc = etDesc.text.toString().trim()
            val priceStr = etPrice.text.toString().trim()
            val category = etCategory.text.toString().trim()
            val stockStr = etStock.text.toString().trim()

            if (name.isEmpty() || desc.isEmpty() || priceStr.isEmpty() || category.isEmpty() || stockStr.isEmpty()) {
                Toast.makeText(this, "يرجى ملء جميع الحقول الإدارية", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = priceStr.toDoubleOrNull() ?: 0.0
            val stock = stockStr.toIntOrNull() ?: 0

            // تجهيز كائن المنتج الجديد بدون رابط صورة حالياً (افتراضي)
            val newProduct = Product(
                name = name,
                description = desc,
                price = price,
                category = category,
                stockCount = stock,
                imageUrl = "default_image"
            )

            // استدعاء دالة الإضافة السحابية
            FirebaseManager.addProduct(newProduct) { success ->
                if (success) {
                    Toast.makeText(this, "تم رفع المنتج الجديد لمتجر الحسام بنجاح!", Toast.LENGTH_LONG).show()
                    // تنظيف الخانات لإدخال منتج آخر
                    etName.text.clear()
                    etDesc.text.clear()
                    etPrice.text.clear()
                    etCategory.text.clear()
                    etStock.text.clear()
                } else {
                    Toast.makeText(this, "فشل الرفع، يرجى التحقق من الاتصال", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
