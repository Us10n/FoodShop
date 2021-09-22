package com.example.foodshop.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.databinding.ItemBinding

class MenuViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MenuPosition) {
        binding.title.text = item.name
    }
}