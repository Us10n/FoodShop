package com.example.foodshop.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodshop.Repository
import com.example.foodshop.callback.OfferCallBack
import com.example.foodshop.recycler.OfferPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OffersFragmentViewModel(private val repository: Repository) : ViewModel() {
    private val liveOffer: MutableLiveData<List<OfferPosition>> = MutableLiveData()
    val offers: LiveData<List<OfferPosition>>
        get() = liveOffer

    fun loadOffers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadOffers(object : OfferCallBack {
                override fun onCallback(positionsList: List<OfferPosition>) {
                    liveOffer.postValue(positionsList)
                }
            })
        }
    }

    fun loadPosition(url: String, view: ImageView) {
        repository.loadImage(url, view)
    }

}
