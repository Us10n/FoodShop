package com.example.foodshop.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.ShavaHolder
import com.example.foodshop.ShavaListener
import com.example.foodshop.databinding.MenuPositionBinding
import com.example.foodshop.recycler.MenuPosition

class MenuPositionViewHolder (private val binding: MenuPositionBinding, private val holder:ShavaHolder) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MenuPosition, listener: ShavaListener) {
        listener.loadImage("https://firebasestorage.googleapis.com/v0/b/test-4c0c2.appspot.com/o/imgs%2Ftest1.png?alt=media&token=6c3bfcbd-d8f2-4d63-aae9-34d6d84ef468",binding.shaurmaimg)

        binding.addToCart.setOnClickListener {
            holder.addShava(item)
        }
    }
}