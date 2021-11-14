package com.example.foodshop.callback

import com.example.foodshop.database.FoodPosition

interface MenuCallBack {
    fun onCallback(positionsList: List<FoodPosition>)
}