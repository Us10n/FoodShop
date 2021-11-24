package com.example.foodshop.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodshop.callback.MenuCallBack
import com.example.foodshop.Repository
import com.example.foodshop.callback.OfferCallBack
import com.example.foodshop.database.FoodPosition
import com.example.foodshop.recycler.OfferPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuFragmentViewModel(private val repository: Repository) : ViewModel() {

    private val liveMenu: MutableLiveData<List<FoodPosition>> = MutableLiveData()
    val positions: LiveData<List<FoodPosition>>
        get() = liveMenu

    private val liveOffer: MutableLiveData<MutableList<OfferPosition>> = MutableLiveData()
    val offers: LiveData<MutableList<OfferPosition>>
        get() = liveOffer

    fun loadOffers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadOffers(object : OfferCallBack {
                override fun onCallback(positionsList: List<OfferPosition>) {
                    val list = positionsList as MutableList<OfferPosition>
                    list.add(OfferPosition("Free cola","https://grilltochka.com/wp-content/uploads/2020/08/shaurma600cola.jpg","Free cola\n*Only in caffe"))
                    liveOffer.postValue(list)
                }
            })
        }
    }
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