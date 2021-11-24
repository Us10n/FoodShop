package com.example.foodshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.foodshop.databinding.ItemBinding
import com.example.foodshop.recycler.MenuViewHolder
import com.example.foodshop.recycler.OfferMenuDiffCallback
import com.example.foodshop.recycler.OfferPosition

class MenuAdapter : ListAdapter<OfferPosition, MenuViewHolder>(OfferMenuDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}