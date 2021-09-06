package com.example.foodshop.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodshop.Repository

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuFragmentViewModel::class.java)) {
            return MenuFragmentViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(AccountFragmentViewModel::class.java)) {
            return AccountFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}