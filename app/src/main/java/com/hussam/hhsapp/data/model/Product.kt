package com.hussam.hhsapp.data.model

data class Product(
    val id: String = "",          // معرف المنتج الفريد
    val name: String = "",        // اسم المنتج (مثلاً: شاحن سريع، جالكسي)
    val description: String = "", // وصف المنتج ومميزاته
    val price: Double = 0.0,      // سعر المنتج
    val imageUrl: String = "",    // رابط صورة المنتج
    val category: String = "",    // القسم (هواتف، إكسسوارات، مستحضرات)
    val stockCount: Int = 0       // الكمية المتوفرة في المخزن
)
