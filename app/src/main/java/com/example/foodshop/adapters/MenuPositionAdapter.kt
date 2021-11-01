package com.example.foodshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.foodshop.ShavaHolder
import com.example.foodshop.listener.ShavaListener
import com.example.foodshop.databinding.MenuPositionBinding
import com.example.foodshop.fragment.FullScreenFragment
import com.example.foodshop.listener.FullScreenListener
import com.example.foodshop.recycler.MenuDiffCallback
import com.example.foodshop.recycler.FoodPosition
import com.example.foodshop.recycler.MenuPositionViewHolder

class MenuPositionAdapter(
    private val holder: ShavaHolder,
    private val listener: ShavaListener,
    private val listenerFull: FullScreenListener
) :
    ListAdapter<FoodPosition, MenuPositionViewHolder>(
        MenuDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuPositionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MenuPositionBinding.inflate(layoutInflater, parent, false)
        return MenuPositionViewHolder(binding, holder, listenerFull)
    }

    override fun onBindViewHolder(holder: MenuPositionViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, listener) }
    }
}