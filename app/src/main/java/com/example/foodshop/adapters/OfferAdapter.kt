package com.example.foodshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.foodshop.ShavaHolder
import com.example.foodshop.databinding.MenuPositionBinding
import com.example.foodshop.databinding.OfferItemBinding
import com.example.foodshop.listener.FullScreenListener
import com.example.foodshop.listener.ShavaListener
import com.example.foodshop.recycler.MenuPositionViewHolder
import com.example.foodshop.recycler.OfferDiffCallback
import com.example.foodshop.recycler.OfferPosition
import com.example.foodshop.recycler.OfferViewHolder

class OfferAdapter(
    private val holder: ShavaHolder,
    private val listener: ShavaListener,
    private val listenerFull: FullScreenListener
) :
    ListAdapter<OfferPosition, OfferViewHolder>(
        OfferDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OfferItemBinding.inflate(layoutInflater, parent, false)
        return OfferViewHolder(binding, holder, listenerFull)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, listener) }
    }

}