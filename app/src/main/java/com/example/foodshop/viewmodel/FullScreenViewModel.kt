package com.example.foodshop.viewmodel

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.example.foodshop.Repository

class FullScreenViewModel(private val repository: Repository) : ViewModel() {
    fun loadImage(url: String, view: ImageView) {
        repository.loadImage(url,view)
    }
}