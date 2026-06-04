package com.hussam.hhsapp.data.cart

import com.hussam.hhsapp.data.model.CartItem
import com.hussam.hhsapp.data.model.Product

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addProduct(product: Product) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity += 1
        } else {
            cartItems.add(CartItem(product))
        }
    }

    // دالة حذف منتج معين بالكامل من السلة
    fun removeProduct(productId: String) {
        cartItems.removeAll { it.product.id == productId }
    }

    fun getCartItems(): List<CartItem> = cartItems

    fun getTotalPrice(): Double {
        return cartItems.sumOf { it.product.price * it.quantity }
    }

    fun clearCart() {
        cartItems.clear()
    }
}
