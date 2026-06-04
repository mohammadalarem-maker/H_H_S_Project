package com.hussam.hhsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.hussam.hhsapp.R
import com.hussam.hhsapp.MainActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            // 1. عرض الرسالة
            Toast.makeText(this, "جاري تسجيل الدخول...", Toast.LENGTH_SHORT).show()
            
            // 2. كود الانتقال المباشر
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            
            // 3. إنهاء صفحة الدخول (حتى لا يعود المستخدم للخلف)
            finish()
        }
    }
}
