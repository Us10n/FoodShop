package com.example.foodshop.database

import android.util.Log
import android.widget.ImageView
import com.example.foodshop.Exception
import com.example.foodshop.MyCallBack
import com.example.foodshop.recycler.MenuPosition
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.squareup.picasso.Picasso

class Database {

    fun loadImgByUrl(url: String, view: ImageView) {
        Picasso.get()
            .load(url)
            .into(view)
    }

    suspend fun uploadMenuPosition(item: MenuPosition) {
        val db = FirebaseFirestore.getInstance()
        db.collection("shaurma")
            .add(item)
            .addOnSuccessListener {
                Log.d("mine", "send to db")
            }
            .addOnFailureListener {
                Log.d("mine", "error to db")
                throw Exception("Database add", it)
            }
    }

    suspend fun loadMenuPositions(myCallBack: MyCallBack): List<MenuPosition> {
        var list = mutableListOf<MenuPosition>()
        val db = FirebaseFirestore.getInstance()
        db.collection("shaurma")
            .get()
            .addOnSuccessListener { result ->
                for (document in result.documents) {
                    Log.d(
                        "mine",
                        "${document.id} => ${document.data} =>${document.toObject<MenuPosition>()}"
                    )
                    list.add(document.toObject<MenuPosition>()!!);
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