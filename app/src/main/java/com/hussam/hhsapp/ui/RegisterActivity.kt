package com.hussam.hhsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hussam.hhsapp.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    // دالة لتصحيح الـ Toast وتجنب مشاكل النطاق (Scope)
    fun showToast(message: String) {
        Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_SHORT).show()
    }

    // دالة لتصحيح الـ finish
    fun closeActivity() {
        this@RegisterActivity.finish()
    }
}
