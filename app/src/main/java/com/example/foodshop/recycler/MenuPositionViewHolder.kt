package com.example.foodshop.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.ShavaHolder
import com.example.foodshop.ShavaListener
import com.example.foodshop.databinding.MenuPositionBinding

class MenuPositionViewHolder (private val binding: MenuPositionBinding, private val holder:ShavaHolder) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MenuPosition, listener: ShavaListener) {
        listener.loadImage(item.imgUrl,binding.shaurmaimg)

        binding.addToCart.setOnClickListener {
            holder.addShava(item)
        }
    }
}