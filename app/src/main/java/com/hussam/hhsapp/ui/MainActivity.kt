package com.hussam.hhsapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hussam.hhsapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ربط الأزرار الخمسة الأساسية للوحة التحكم الجديدة
        val btnPhones = findViewById<LinearLayout>(R.id.btnPhones)
        val btnAccessories = findViewById<LinearLayout>(R.id.btnAccessories)
        val btnCosmetics = findViewById<LinearLayout>(R.id.btnCosmetics)
        val btnOpenCart = findViewById<LinearLayout>(R.id.btnOpenCart)
        val btnOpenProfile = findViewById<LinearLayout>(R.id.btnOpenProfile)

        // جلب العناصر القديمة ديناميكياً باسمها النصي لتفادي أخطاء البناء تماماً
        val tvUserEmailId = resources.getIdentifier("tvUserEmail", "id", packageName)
        val tvUserEmail = if (tvUserEmailId != 0) findViewById<TextView>(tvUserEmailId) else null

        val btnLogoutId = resources.getIdentifier("btnLogout", "id", packageName)
        val btnLogout = if (btnLogoutId != 0) findViewById<View>(btnLogoutId) else null

        // تفعيل الأزرار والبطاقات التجارية للتنقل بسلاسة
        btnPhones?.setOnClickListener {
            try {
                startActivity(Intent(this, ProductListActivity::class.java))
            } catch (e: Exception) {
                Toast.makeText(this, "جاري تجهيز قسم الهواتف الذكية", Toast.LENGTH_SHORT).show()
            }
        }

        btnAccessories?.setOnClickListener {
            try {
                startActivity(Intent(this, ProductListActivity::class.java))
            } catch (e: Exception) {
                Toast.makeText(this, "جاري تجهيز قسم الإكسسوارات", Toast.LENGTH_SHORT).show()
            }
        }

        btnCosmetics?.setOnClickListener {
            try {
                startActivity(Intent(this, ProductListActivity::class.java))
            } catch (e: Exception) {
                Toast.makeText(this, "جاري تجهيز قسم مستحضرات التجميل", Toast.LENGTH_SHORT).show()
            }
        }

        btnOpenCart?.setOnClickListener {
            try {
                startActivity(Intent(this, CartActivity::class.java))
            } catch (e: Exception) {
                Toast.makeText(this, "جاري فتح عربة التسوق", Toast.LENGTH_SHORT).show()
            }
        }

        btnOpenProfile?.setOnClickListener {
            try {
                startActivity(Intent(this, ProfileActivity::class.java))
            } catch (e: Exception) {
                Toast.makeText(this, "جاري فتح الملف الشخصي", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogout?.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
