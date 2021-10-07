package com.example.foodshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodshop.MyCallBack
import com.example.foodshop.Repository
import com.example.foodshop.recycler.MenuPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OffersFragmentViewModel(private val repository: Repository) : ViewModel() {

    private val liveMenu: MutableLiveData<List<MenuPosition>> = MutableLiveData()
    val positions: LiveData<List<MenuPosition>>
        get() = liveMenu

    fun loadMenuPositions() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadMenuPositions(object: MyCallBack{
                override fun onCallback(positionsList: List<MenuPosition>) {
                    liveMenu.value=positionsList
                }
            })
        }


    }

    fun uploadMenuPosition(position: MenuPosition) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.uploadMenuPosition(position)
        }
    }

}
