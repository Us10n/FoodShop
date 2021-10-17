package com.example.foodshop.recycler

data class FoodPosition(
    var id: Long = 0,
    var name: String = "",
    var imgUrl: String = "",
    var description: String = "",
    var price: List<Double> = arrayListOf(),
    var weight: List<Double> = arrayListOf()
)
