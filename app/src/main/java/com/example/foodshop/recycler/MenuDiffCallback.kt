package com.example.foodshop.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.foodshop.database.FoodPosition

class MenuDiffCallback : DiffUtil.ItemCallback<FoodPosition>() {
    override fun areItemsTheSame(oldItem: FoodPosition, newItem: FoodPosition): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FoodPosition, newItem: FoodPosition): Boolean {
        return oldItem == newItem
    }
}