package com.example.foodshop.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.foodshop.ShavaHolder
import com.example.foodshop.databinding.ItemBinding
import com.example.foodshop.databinding.MenuPositionBinding

class MenuPositionAdapter(private val holder:ShavaHolder): ListAdapter<MenuPosition, MenuPositionViewHolder>(MenuDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuPositionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MenuPositionBinding.inflate(layoutInflater, parent, false)
        return MenuPositionViewHolder(binding, holder)
    }

    override fun onBindViewHolder(holder: MenuPositionViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


}