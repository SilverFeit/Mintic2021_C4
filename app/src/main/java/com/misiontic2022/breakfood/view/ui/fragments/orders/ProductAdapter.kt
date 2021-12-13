package com.misiontic2022.breakfood.view.ui.fragments.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misiontic2022.breakfood.R
import com.misiontic2022.breakfood.view.ui.datasource.firebase.firebaseDTOs.ProductsFirebaseDTO
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val listProducts: List<ProductsFirebaseDTO>,
    private val clickListener: (product: ProductsFirebaseDTO) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, null, false)
        view.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(view = view, clickListener = clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listProducts.get(position))
    }

    override fun getItemCount(): Int {
        return listProducts.size
    }

    inner class ViewHolder(
        val view: View,
        val clickListener: (product: ProductsFirebaseDTO) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private var imageProduct: ImageView = view.findViewById(R.id.imImageProduct)
        private var nameProduct: TextView = view.findViewById(R.id.tvNameProduct)
        private var price: TextView = view.findViewById(R.id.tvPriceProduct)

        fun bind(product: ProductsFirebaseDTO) {
            updateImage(product.url)
            nameProduct.setText(product.name)
            price.setText(product.price)
            view.setOnClickListener {
                clickListener.invoke(product)
            }
        }

        private fun updateImage(url: String?) {
            if (url == null) return
            Picasso
                .get()
                .load(url)
                .into(imageProduct)
        }
    }
}