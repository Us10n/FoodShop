package com.example.foodshop.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.ShavaHolder
import com.example.foodshop.database.FoodPosition
import com.example.foodshop.databinding.MenuPositionBinding
import com.example.foodshop.listener.FullScreenListener
import com.example.foodshop.listener.ShavaListener

class MenuPositionViewHolder(
    private val binding: MenuPositionBinding,
    private val holder: ShavaHolder,
    private val listenerFull: FullScreenListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FoodPosition, listener: ShavaListener) {
        listener.loadImage(item.imgUrl, binding.shaurmaimg)

        binding.addToCart.setOnClickListener {
            listenerFull.createFullScreen(item.imgUrl, item.name)
        }

        binding.shaurmaimg.setOnClickListener {
            listenerFull.createFullScreen(item.imgUrl, item.name)
        }
    }
}