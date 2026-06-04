package com.hussam.hhsapp.data.model

data class Order(
    val orderId: String = "",
    val userId: String = "",
    val userEmail: String = "",
    val date: String = "",
    val totalPrice: Double = 0.0,
    val status: String = "Pending",
    val items: List<CartItem> = emptyList()
)
