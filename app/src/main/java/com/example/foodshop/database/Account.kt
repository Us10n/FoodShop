package com.example.foodshop.database

import com.google.firebase.firestore.DocumentReference

data class Account(
    val id: String = "",
    val name: String = "",
    val phone: String = "",
    val imgUrl: String = "",
    val orders: List<DocumentReference> = arrayListOf()
) {
    fun isEmpty(): Boolean =
        id == "" && name == "" && phone == "" && orders.isEmpty()
}
