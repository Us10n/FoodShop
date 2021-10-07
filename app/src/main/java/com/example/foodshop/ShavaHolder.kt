package com.example.foodshop

import com.example.foodshop.recycler.MenuPosition
import com.example.foodshop.recycler.ShoppingCartPosition

object ShavaHolder {
    private val order = mutableListOf<MenuPosition>()

    fun addShava(item: MenuPosition) {
        order.add(item)
    }

    fun getSpecialList(): List<ShoppingCartPosition> {
        var specialList = mutableSetOf<ShoppingCartPosition>()
        order.forEach {
            var number = 0
            for (item in order) {
                if (item.name.equals(it.name)) {
                    number++
                }
            }
            specialList.add(ShoppingCartPosition(it, number))
        }
        return specialList.toList()
    }
}