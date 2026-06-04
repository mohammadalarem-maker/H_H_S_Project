package com.hussam.hhsapp.data.model

data class CartItem(
    val product: Product,    // المنتج المختار
    var quantity: Int = 1    // الكمية المطلوبة من هذا المنتج
)
