package com.example.foodshop.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.foodshop.databinding.OrderHistoryItemBinding

class HistoryViewHolder(private val binding: OrderHistoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(item: HistoryPosition) {
        binding.cashBack.text = "+ " + item.cashback
        binding.orderBody.text=item.body
    }
}