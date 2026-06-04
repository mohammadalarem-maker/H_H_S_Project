package com.hussam.hhsapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.appcompat.app.AppCompatActivity
import com.hussam.hhsapp.R
import com.hussam.hhsapp.firebase.FirebaseManager

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "يرجى ملء جميع الحقول", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // استدعاء محرك الفيربيز الذي جهزناه لتسجيل الدخول
            FirebaseManager.loginWithEmail(email, password) { success, errorMessage ->
                if (success) {
                    Toast.makeText(this, "تم تسجيل الدخول بنجاح! أهلاً بك في الحسام", Toast.LENGTH_LONG).show()
                    // هنا ننتقل لاحقاً للشاشة الرئيسية للمتجر
                } else {
                    Toast.makeText(this, "خطأ: $errorMessage", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
