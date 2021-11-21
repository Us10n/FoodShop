package com.example.foodshop.viewmodel

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodshop.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FullScreenViewModel(private val repository: Repository) : ViewModel() {
    fun loadImage(url: String, view: ImageView) {
        repository.loadImage(url, view)
    }
}