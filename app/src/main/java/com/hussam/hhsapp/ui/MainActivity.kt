package com.hussam.hhsapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.appcompat.app.AppCompatActivity
import com.hussam.hhsapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPhones = findViewById<Button>(R.id.btnPhones)
        val btnAccessories = findViewById<Button>(R.id.btnAccessories)
        val btnCosmetics = findViewById<Button>(R.id.btnCosmetics)

        // برمجة الانتقال أو جلب بضائع كل قسم عند الضغط عليه
        btnPhones.setOnClickListener {
            Toast.makeText(this, "جاري فتح قسم الهواتف...", Toast.LENGTH_SHORT).show()
            // هنا سنربطه لاحقاً بشاشة عرض قائمة البضائع للقسم المختار
        }

        btnAccessories.setOnClickListener {
            Toast.makeText(this, "جاري فتح قسم الإكسسوارات...", Toast.LENGTH_SHORT).show()
        }

        btnCosmetics.setOnClickListener {
            Toast.makeText(this, "جاري فتح قسم مستحضرات التجميل...", Toast.LENGTH_SHORT).show()
        }
    }
}
