package com.hussam.hhsapp.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.hussam.hhsapp.data.model.User
import com.hussam.hhsapp.data.model.Product
import com.hussam.hhsapp.data.model.Order

object FirebaseManager {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private val usersRef = database.getReference("users")
    private val productsRef = database.getReference("products")
    private val ordersRef = database.getReference("orders")

    fun getCurrentUserEmail(): String {
        return auth.currentUser?.email ?: "ضيف غريب"
    }

    // --- الحسابات ---
    fun registerWithEmail(user: User, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(user.email, password)
            .addOnSuccessListener { result ->
                val uId = result.user?.uid ?: ""
                val updatedUser = user.copy(id = uId)
                usersRef.child(uId).setValue(updatedUser)
                    .addOnSuccessListener { onComplete(true, null) }
                    .addOnFailureListener { e -> onComplete(true, e.message) }
            }
            .addOnFailureListener { e -> onComplete(false, e.message) }
    }

    fun loginWithEmail(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onComplete(true, null) }
            .addOnFailureListener { e -> onComplete(false, e.message) }
    }

    // --- المنتجات ---
    fun addProduct(product: Product, onComplete: (Boolean) -> Unit) {
        val newKey = productsRef.push().key ?: return onComplete(false)
        val finalProduct = product.copy(id = newKey)
        productsRef.child(newKey).setValue(finalProduct)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    fun getProductsByCategory(category: String, onResult: (List<Product>) -> Unit) {
        productsRef.orderByChild("category").equalTo(category).get()
            .addOnSuccessListener { snapshot ->
                val productList = mutableListOf<Product>()
                if (snapshot.exists()) {
                    for (child in snapshot.children) {
                        val product = child.getValue(Product::class.java)
                        if (product != null) {
                            productList.add(product)
                        }
                    }
                }
                onResult(productList)
            }
            .addOnFailureListener { onResult(emptyList()) }
    }

    // --- الطلبات ---
    fun placeOrder(order: Order, onComplete: (Boolean) -> Unit) {
        val newOrderKey = ordersRef.push().key ?: return onComplete(false)
        val finalOrder = order.copy(orderId = newOrderKey)
        ordersRef.child(newOrderKey).setValue(finalOrder)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    // جلب كل الطلبات سحابياً (خاص بلوحة الإدارة)
    fun getAllOrders(onResult: (List<Order>) -> Unit) {
        ordersRef.get().addOnSuccessListener { snapshot ->
            val orderList = mutableListOf<Order>()
            if (snapshot.exists()) {
                for (child in snapshot.children) {
                    val order = child.getValue(Order::class.java)
                    if (order != null) orderList.add(order)
                }
            }
            onResult(orderList)
        }.addOnFailureListener { onResult(emptyList()) }
    }

    // جلب طلبات زبون محدد فقط بناءً على إيميله
    fun getOrdersByUser(email: String, onResult: (List<Order>) -> Unit) {
        ordersRef.orderByChild("userEmail").equalTo(email).get().addOnSuccessListener { snapshot ->
            val orderList = mutableListOf<Order>()
            if (snapshot.exists()) {
                for (child in snapshot.children) {
                    val order = child.getValue(Order::class.java)
                    if (order != null) orderList.add(order)
                }
            }
            onResult(orderList)
        }.addOnFailureListener { onResult(emptyList()) }
    }
}
