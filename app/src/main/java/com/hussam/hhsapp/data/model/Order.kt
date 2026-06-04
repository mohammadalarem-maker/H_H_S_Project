package com.hussam.hhsapp.data.model

data class Order(
    val id: String = "",
    val userId: String = "",
    val date: String = "",
    val totalPrice: Double = 0.0,
    val status: String = "Pending",
    val items: List<CartItem> = emptyList()
)
