package com.example.foodshop.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.ShavaHolder
import com.example.foodshop.databinding.OfferItemBinding
import com.example.foodshop.listener.FullScreenListener
import com.example.foodshop.listener.ShavaListener

class OfferViewHolder(
    private val binding: OfferItemBinding,
    private val holder: ShavaHolder,
    private val listenerFull: FullScreenListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: OfferPosition, listener: ShavaListener) {
        listener.loadImage(item.imgUrl, binding.offerImage)
        binding.offerText.text = item.name
    }
}