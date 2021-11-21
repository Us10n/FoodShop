package com.example.foodshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodshop.Repository
import com.example.foodshop.recycler.HistoryPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderFragmentViewModel(private val repository: Repository) : ViewModel() {
    fun addOrderToHistory(order: HistoryPosition) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addOrder(order)
        }
    }


}