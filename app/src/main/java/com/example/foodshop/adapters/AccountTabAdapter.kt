package com.example.foodshop.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodshop.fragment.OrderHistoryFragment
import com.example.foodshop.fragment.ReturnsFragment

class AccountTabAdapter(fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ReturnsFragment()
            }
            1 -> {
                OrderHistoryFragment()
            }
            else -> {
                Fragment()
            }
        }
    }


}