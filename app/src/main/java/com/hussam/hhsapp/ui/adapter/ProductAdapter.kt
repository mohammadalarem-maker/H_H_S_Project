package com.hussam.hhsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hussam.hhsapp.R
import com.hussam.hhsapp.data.model.Product

class ProductAdapter(private val products: List<Product>) : 
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvProductName)
        val tvDescription: TextView = view.findViewById(R.id.tvProductDescription)
        val tvPrice: TextView = view.findViewById(R.id.tvProductPrice)
        val ivImage: ImageView = view.findViewById(R.id.ivProductImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.tvName.text = product.name
        holder.tvDescription.text = product.description
        holder.tvPrice.text = "${product.price} YER" // العملة الافتراضية للمتجر بالريال

        // ملاحظة: هنا سنستخدم مكتبة (Glide أو Picasso) لاحقاً لتحميل رابط الصورة الحقيقي سحابياً
        holder.ivImage.setImageResource(R.mipmap.ic_launcher)
    }

    override fun getItemCount(): Int = products.size
}
