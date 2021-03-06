package com.example.foodshop.viewmodel

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
        if (modelClass.isAssignableFrom(OffersFragmentViewModel::class.java)) {
            return OffersFragmentViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(OrderFragmentViewModel::class.java)) {
            return OrderFragmentViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(EntryFragmentViewModel::class.java)) {
            return EntryFragmentViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(FullScreenViewModel::class.java)) {
            return FullScreenViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(FullOfferViewModel::class.java)) {
            return FullOfferViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}