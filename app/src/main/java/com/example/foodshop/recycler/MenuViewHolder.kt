package com.example.foodshop.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.R
import com.example.foodshop.database.FoodPosition
import com.example.foodshop.databinding.ItemBinding
import com.squareup.picasso.Picasso

class MenuViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: OfferPosition) {
        Picasso.get()
            .load(item.imgUrl)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(binding.image)
    }
}