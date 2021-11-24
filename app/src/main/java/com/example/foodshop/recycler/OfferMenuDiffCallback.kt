package com.example.foodshop.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.foodshop.database.FoodPosition

class OfferMenuDiffCallback : DiffUtil.ItemCallback<OfferPosition>() {
    override fun areItemsTheSame(oldItem: OfferPosition, newItem: OfferPosition): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: OfferPosition, newItem: OfferPosition): Boolean {
        return oldItem == newItem
    }
}