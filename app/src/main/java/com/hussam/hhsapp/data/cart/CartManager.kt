package com.hussam.hhsapp.data.cart

import com.hussam.hhsapp.data.model.CartItem
import com.hussam.hhsapp.data.model.Product

object CartManager {
    // قائمة محليّة لحفظ عناصر السلة أثناء تصفح التطبيق
    private val cartItems = mutableListOf<CartItem>()

    // إضافة منتج للسلة، وإذا كان موجوداً مسبقاً تزيد الكمية فقط
    fun addProduct(product: Product) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity += 1
        } else {
            cartItems.add(CartItem(product))
        }
    }

    // جلب كل العناصر الموجودة في السلة حالياً
    fun getCartItems(): List<CartItem> = cartItems

    // حساب السعر الإجمالي لجميع المنتجات في السلة
    fun getTotalPrice(): Double {
        return cartItems.sumOf { it.product.price * it.quantity }
    }

    // تفريغ السلة بالكامل بعد إتمام الطلب بنجاح
    fun clearCart() {
        cartItems.clear()
    }
}
