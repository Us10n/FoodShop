package com.example.foodshop.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.databinding.CartPositionBinding

class CartViewHolder(private val binding: CartPositionBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ShoppingCartPosition) {
        binding.title.text = item.item.name
        binding.priceTitle.text = (item.item.price.toInt()*item.number).toString()
        binding.numberTitle.text = item.number.toString()
    }
}