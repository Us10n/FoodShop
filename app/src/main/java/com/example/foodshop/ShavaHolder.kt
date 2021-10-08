package com.example.foodshop

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.foodshop.recycler.MenuPosition
import com.example.foodshop.recycler.ShoppingCartPosition

object ShavaHolder {
    private val order = mutableListOf<MenuPosition>()
    private var special = mutableSetOf<ShoppingCartPosition>()

    fun addShava(item: MenuPosition) {
        order.add(item)
    }

    fun getSpecialList(): List<ShoppingCartPosition> {
        val specialList = mutableSetOf<ShoppingCartPosition>()
        order.forEach {
            var number = 0
            for (item in order) {
                if (item.name.equals(it.name)) {
                    number++
                }
            }
            specialList.add(ShoppingCartPosition(it, number))
        }
        special = specialList
        return specialList.toList()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteItem(item: ShoppingCartPosition) {
        special.remove(item)
        println(special.size)
        order.removeIf { it.name==item.item.name }
    }
}