package com.example.foodshop.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.databinding.ItemBinding
import com.example.foodshop.databinding.MenuPositionBinding

class MenuPositionViewHolder (private val binding: MenuPositionBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MenuPosition) {
        binding.title.text = item.name
    }
}