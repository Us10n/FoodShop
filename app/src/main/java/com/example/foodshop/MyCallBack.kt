package com.example.foodshop

import com.example.foodshop.recycler.MenuPosition

interface MyCallBack {
    fun onCallback(positionsList: List<MenuPosition>)
}