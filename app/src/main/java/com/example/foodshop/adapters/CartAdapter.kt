package com.example.foodshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.foodshop.databinding.CartPositionBinding
import com.example.foodshop.recycler.*

class CartAdapter : ListAdapter<ShoppingCartPosition, CartViewHolder>(CartDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartPositionBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}