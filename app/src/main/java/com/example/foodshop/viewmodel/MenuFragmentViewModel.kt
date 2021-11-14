package com.example.foodshop.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodshop.callback.MenuCallBack
import com.example.foodshop.Repository
import com.example.foodshop.database.FoodPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuFragmentViewModel(private val repository: Repository) : ViewModel() {

    private val liveMenu: MutableLiveData<List<FoodPosition>> = MutableLiveData()
    val positions: LiveData<List<FoodPosition>>
        get() = liveMenu


    fun loadMenuPositions() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadMenuPositions(object : MenuCallBack {
                override fun onCallback(positionsList: List<FoodPosition>) {
                    liveMenu.value = positionsList
                }
            })
        }
    }

    fun loadPosition(url: String, view: ImageView) {
        repository.loadImage(url, view)
    }
}