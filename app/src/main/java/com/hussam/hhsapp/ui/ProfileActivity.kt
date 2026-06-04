package com.hussam.hhsapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.hussam.hhsapp.R
import com.hussam.hhsapp.firebase.FirebaseManager

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val tvEmail = findViewById<TextView>(R.id.tvProfileEmail)
        val btnOrderHistory = findViewById<Button>(R.id.btnOrderHistory)
        val btnAdmin = findViewById<Button>(R.id.btnAdminPanel)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        val currentEmail = FirebaseManager.getCurrentUserEmail()
        tvEmail.text = "الحساب المستخدم: $currentEmail"

        if (currentEmail == "admin@hussam.com" || currentEmail.contains("hussam")) {
            btnAdmin.visibility = View.VISIBLE
        }

        // فتح شاشة سجل الطلبات
        btnOrderHistory.setOnClickListener {
            val intent = Intent(this, OrderHistoryActivity::class.java)
            startActivity(intent)
        }

        btnAdmin.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, "تم تسجيل الخروج بنجاح", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
