package com.example.foodshop.viewmodel

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.example.foodshop.Repository
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AccountFragmentViewModel(private val repository: Repository) : ViewModel() {
    fun loadImage(url: String, view: ImageView) {
        repository.loadImage(url, view)
    }

    fun initTabMediator(tabLayout: TabLayout, vPager: ViewPager2) {
        TabLayoutMediator(tabLayout, vPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Returns"
                }
                1 -> {
                    tab.text = "Order History"
                }
            }
        }.attach()
    }

}