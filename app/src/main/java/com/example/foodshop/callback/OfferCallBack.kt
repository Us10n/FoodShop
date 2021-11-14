package com.example.foodshop.callback

import com.example.foodshop.recycler.OfferPosition

interface OfferCallBack {
    fun onCallback(positionsList: List<OfferPosition>)
}