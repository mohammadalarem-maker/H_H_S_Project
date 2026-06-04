package com.hussam.hhsapp.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hussam.hhsapp.R
import com.hussam.hhsapp.firebase.FirebaseManager
import com.hussam.hhsapp.ui.adapter.OrderAdapter

class OrderHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        val tvTitle = findViewById<TextView>(R.id.tvOrderHistoryTitle)
        val rvOrders = findViewById<RecyclerView>(R.id.rvOrderHistory)
        rvOrders.layoutManager = LinearLayoutManager(this)

        val currentEmail = FirebaseManager.getCurrentUserEmail()

        // فحص: إذا كان الإيميل للمدير، يجلب كل طلبات المتجر، وإلا يجلب طلبات الحساب الحالي فقط
        if (currentEmail == "admin@hussam.com" || currentEmail.contains("hussam")) {
            tvTitle.text = "وارد طلبات الزبائن (إدارة) 📥"
            FirebaseManager.getAllOrders { orderList ->
                if (orderList.isEmpty()) Toast.makeText(this, "لا توجد طلبات واردة حالياً", Toast.LENGTH_SHORT).show()
                rvOrders.adapter = OrderAdapter(orderList)
            }
        } else {
            tvTitle.text = "طلباتي السابقة 📦"
            FirebaseManager.getOrdersByUser(currentEmail) { orderList ->
                if (orderList.isEmpty()) Toast.makeText(this, "ليس لديك طلبات سابقة", Toast.LENGTH_SHORT).show()
                rvOrders.adapter = OrderAdapter(orderList)
            }
        }
    }
}
