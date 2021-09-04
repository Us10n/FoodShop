package com.example.foodshop

import com.example.foodshop.recycler.MenuPosition

object ShavaHolder {
    private val order = mutableListOf<MenuPosition>()

    fun addShava(item: MenuPosition) {
        order.add(item)
    }
    fun getList():List<MenuPosition>{
        return order
    }
}