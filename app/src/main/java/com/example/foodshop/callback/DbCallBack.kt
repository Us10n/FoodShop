package com.example.foodshop.callback

import com.example.foodshop.recycler.FoodPosition

interface DbCallBack {
    fun onCallback(positionsList: List<FoodPosition>)
}