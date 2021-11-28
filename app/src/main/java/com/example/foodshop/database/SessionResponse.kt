package com.example.foodshop.database

data class SessionResponse(
    val mac: String = "",
    val number: String = ""
) {
    fun isEmpty(): Boolean = mac.isEmpty() || number.isEmpty()
}
