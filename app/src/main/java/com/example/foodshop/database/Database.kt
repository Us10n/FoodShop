package com.example.foodshop.database

import android.util.Log
import android.widget.ImageView
import com.example.foodshop.callback.AccountCallBack
import com.example.foodshop.callback.MenuCallBack
import com.example.foodshop.callback.OfferCallBack
import com.example.foodshop.exception.Exception
import com.example.foodshop.recycler.OfferPosition
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.squareup.picasso.Picasso

class Database {

    fun loadImgByUrl(url: String, view: ImageView) {
        if(url.isEmpty() || url.isBlank()){
            return
        }
        Picasso.get()
            .load(url)
//            .resize(view.maxWidth, view.maxHeight)
//            .centerCrop()
            .into(view)
    }

    fun loadMenuPositions(myCallBack: MenuCallBack) {
        val db = FirebaseFirestore.getInstance()
        db.collection("shaurma")
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<FoodPosition>()
                for (document in result.documents) {
                    list.add(document.toObject<FoodPosition>()!!)
                }
                myCallBack.onCallback(list)
            }
            .addOnFailureListener {
                Log.d("mine", "Error load menu")
                throw Exception("Database load", it)
            }
    }

    fun loadAccount(id: String, myCallBack: AccountCallBack) {
        val db = FirebaseFirestore.getInstance()
        db.collection("user").whereEqualTo("id", id)
            .get()
            .addOnSuccessListener { result ->
                val documents = result.documents
                val account = if (documents.isEmpty()) Account() else documents[0].toObject<Account>()
                myCallBack.onCallBack(account!!)
            }
            .addOnFailureListener {
                Log.d("mine", "Error upload account")
                throw Exception("Database upload", it)
            }
    }

    fun uploadAccount(account: Account) {
        val db = FirebaseFirestore.getInstance()
        db.collection("user")
            .add(account)
            .addOnFailureListener {
                Log.d("mine", "Error upload account")
                throw Exception("Database upload", it)
            }
    }

    fun loadOffers(myCallBack: OfferCallBack) {
        val db = FirebaseFirestore.getInstance()
        db.collection("offer")
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<OfferPosition>()
                for (document in result.documents) {
                    list.add(document.toObject<OfferPosition>()!!)
                }
                myCallBack.onCallback(list)
            }
            .addOnFailureListener {
                Log.d("mine", "Error load menu")
                throw Exception("Database load", it)
            }
    }
}