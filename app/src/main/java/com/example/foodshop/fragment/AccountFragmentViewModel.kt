package com.example.foodshop.fragment

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.example.foodshop.Repository

class AccountFragmentViewModel(private val repository: Repository) : ViewModel() {
    fun loadImage(url: String, view: ImageView) {
        repository.loadImage(url, view)
    }
}