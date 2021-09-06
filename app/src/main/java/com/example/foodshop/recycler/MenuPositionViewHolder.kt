package com.example.foodshop.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.ShavaHolder
import com.example.foodshop.database.Database
import com.example.foodshop.databinding.ItemBinding
import com.example.foodshop.databinding.MenuPositionBinding

class MenuPositionViewHolder (private val binding: MenuPositionBinding, private val holder:ShavaHolder) : RecyclerView.ViewHolder(binding.root) {
    private val db=Database()
    fun bind(item: MenuPosition) {
        db.loadImgByUrl("https://firebasestorage.googleapis.com/v0/b/test-4c0c2.appspot.com/o/imgs%2Ftest1.png?alt=media&token=6c3bfcbd-d8f2-4d63-aae9-34d6d84ef468",binding.shaurmaimg)

        binding.addToCart.setOnClickListener {
            holder.addShava(item)
        }
    }
}