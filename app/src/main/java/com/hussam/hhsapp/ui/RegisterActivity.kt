package com.hussam.hhsapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.appcompat.app.AppCompatActivity
import com.hussam.hhsapp.R
import com.hussam.hhsapp.data.model.User
import com.hussam.hhsapp.firebase.FirebaseManager

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etUsername = findViewById<EditText>(R.id.etRegUsername)
        val etEmail = findViewById<EditText>(R.id.etRegEmail)
        val etPhone = findViewById<EditText>(R.id.etRegPhone)
        val etPassword = findViewById<EditText>(R.id.etRegPassword)
        val btnDoRegister = findViewById<Button>(R.id.btnDoRegister)

        btnDoRegister.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "يرجى ملء جميع الحقول المطلوبة", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // تجهيز كائن المستخدم الجديد
            val newUser = User(username = username, email = email, phone = phone, role = "customer")

            // إرسال البيانات للتسجيل في فيربيز في الخلفية
            FirebaseManager.registerWithEmail(newUser, password) { success, errorMessage ->
                if (success) {
                    Toast.makeText(this, "تم إنشاء حسابك بنجاح في متجر الحسام!", Toast.LENGTH_LONG).show()
                    finish() // العودة لشاشة الدخول تلقائياً
                } else {
                    Toast.makeText(this, "فشل التسجيل: $errorMessage", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
