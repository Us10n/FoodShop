package com.example.foodshop.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.databinding.CartPositionBinding

class CartViewHolder(private val binding: CartPositionBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(item: ShoppingCartPosition) {
        binding.title.text = item.item.name
        binding.priceTitle.text = (item.item.price[0] * item.number).toString()
        binding.numberTitle.text = item.number.toString()
        if (item.item.description != "") {
            binding.description.text = item.item.description
        } else {
            binding.description.text = "Classic"
        }
    }
}