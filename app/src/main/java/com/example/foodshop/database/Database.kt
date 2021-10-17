package com.example.foodshop.database

import android.util.Log
import android.widget.ImageView
import com.example.foodshop.callback.DbCallBack
import com.example.foodshop.exception.Exception
import com.example.foodshop.recycler.FoodPosition
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.squareup.picasso.Picasso

class Database {

    fun loadImgByUrl(url: String, view: ImageView) {
        Picasso.get()
            .load(url)
            .resize(view.maxWidth, view.maxHeight)
            .centerCrop()
            .into(view)
    }

    fun loadMenuPositions(myCallBack: DbCallBack): List<FoodPosition> {
        val list = mutableListOf<FoodPosition>()
        val db = FirebaseFirestore.getInstance()
        db.collection("shaurma")
            .get()
            .addOnSuccessListener { result ->
                for (document in result.documents) {
                    list.add(document.toObject<FoodPosition>()!!)
                }
                myCallBack.onCallback(list)
            }
            .addOnFailureListener {
                Log.d("mine", "Error get")
                throw Exception("Database get", it)
            }
        return list
    }
}