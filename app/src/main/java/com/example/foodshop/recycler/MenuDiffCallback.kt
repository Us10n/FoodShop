package com.example.foodshop.recycler

import androidx.recyclerview.widget.DiffUtil

class MenuDiffCallback: DiffUtil.ItemCallback<MenuPosition>() {
    override fun areItemsTheSame(oldItem: MenuPosition, newItem: MenuPosition): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: MenuPosition, newItem: MenuPosition): Boolean {
        return oldItem.name == newItem.name
    }
}