package com.example.foodshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodshop.Repository
import com.example.foodshop.recycler.MenuPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class OffersFragmentViewModel(private val repository: Repository) : ViewModel() {

    val LiveMenu: MutableLiveData<List<MenuPosition>> = MutableLiveData()

    fun loadMenuPositions(): List<MenuPosition>? {
        viewModelScope.launch(Dispatchers.IO) {
            LiveMenu.postValue(repository.loadMenuPostions())
        }
        return LiveMenu.value
    }

    fun uploadMenuPosition(position: MenuPosition) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.uploadMenuPosition(position)
        }
    }

}