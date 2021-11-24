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
    private val liveOffer: MutableLiveData<MutableList<OfferPosition>> = MutableLiveData()
    val offers: LiveData<MutableList<OfferPosition>>
        get() = liveOffer

    fun loadOffers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadOffers(object : OfferCallBack {
                override fun onCallback(positionsList: List<OfferPosition>) {
                    val list = positionsList as MutableList<OfferPosition>
                    list.add(OfferPosition("Free cola","https://grilltochka.com/wp-content/uploads/2020/08/shaurma600cola.jpg","Free cola\n" +
                            "*Only in caffe"))
                    liveOffer.postValue(list)
                }
            })
        }
    }

    fun loadPosition(url: String, view: ImageView) {
        repository.loadImage(url, view)
    }

}
