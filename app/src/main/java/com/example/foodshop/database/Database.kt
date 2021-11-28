package com.example.foodshop.database

import android.util.Log
import android.widget.ImageView
import com.example.foodshop.CurrentUser
import com.example.foodshop.R
import com.example.foodshop.callback.*
import com.example.foodshop.exception.Exception
import com.example.foodshop.recycler.HistoryPosition
import com.example.foodshop.recycler.OfferPosition
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.squareup.picasso.Picasso

class Database {

    fun loadImgByUrl(url: String, view: ImageView) {
        if (url.isEmpty() || url.isBlank()) {
            return
        }
        Picasso.get()
            .load(url)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(view)
    }

    fun loadDeviceAuthStatus(mac: String, myCallBack: DeviceStatusCallBack) {
        val db = FirebaseFirestore.getInstance()
        db.collection("session").whereEqualTo("mac", mac)
            .get()
            .addOnSuccessListener { result ->
                val documents = result.documents
                if (!documents.isNullOrEmpty()) {
                    CurrentUser.sessionId = documents[0].id
                    var sessionResponse = documents[0].toObject<SessionResponse>()
                    CurrentUser.account.phone = sessionResponse!!.number
                }
                myCallBack.onCallback(!documents.isNullOrEmpty())
            }
            .addOnFailureListener {
                Log.d("mine", "Error upload account")
                throw Exception("Database upload", it)
            }
    }

    fun loadAccount(number: String, myCallBack: AccountCallBack) {
        val db = FirebaseFirestore.getInstance()
        db.collection("user").whereEqualTo("phone", number)
            .get()
            .addOnSuccessListener { result ->
                val documents = result.documents
                val account =
                    if (documents.isEmpty()) Account() else documents[0].toObject<Account>()
                if (!documents.isNullOrEmpty()) {
                    CurrentUser.accountId = documents[0].id
                }
                myCallBack.onCallBack(account!!)
            }
            .addOnFailureListener {
                Log.d("mine", "Error upload account")
                throw Exception("Database upload", it)
            }
    }

    fun loadAccountHistory(accountId: String, myCallBack: AccountHistoryCallBack) {
        val db = FirebaseFirestore.getInstance()
        db.collection("order").whereEqualTo("uid", accountId)
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<HistoryPosition>()
                for (document in result.documents) {
                    list.add(document.toObject<HistoryPosition>()!!)
                }
                myCallBack.onCallback(list)
            }
            .addOnFailureListener {
                Log.d("mine", "Error upload account")
                throw Exception("Database upload", it)
            }
    }

    fun addSession(mac: String, number: String) {
        val map = hashMapOf<String, String>()
        map["mac"] = mac
        map["number"] = number
        val db = FirebaseFirestore.getInstance()
        db.collection("session")
            .add(map)
            .addOnSuccessListener {
                CurrentUser.sessionId = it.id
            }
            .addOnFailureListener {
                Log.d("mine", "Error upload session")
                throw Exception("Database upload", it)
            }

    }

    fun uploadAccount(account: Account) {
        val db = FirebaseFirestore.getInstance()
        db.collection("user").whereEqualTo("phone", account.phone)
            .get()
            .addOnSuccessListener {
                if (it.documents.isEmpty()) {
                    db.collection("user")
                        .add(account)
                        .addOnSuccessListener {
                            CurrentUser.accountId = it.id
                        }
                        .addOnFailureListener {
                            Log.d("mine", "Error upload account")
                            throw Exception("Database upload", it)
                        }
                } else {
                    CurrentUser.accountId = it.documents[0].id
                }
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

    fun uploadOrder(order: HistoryPosition) {
        val db = FirebaseFirestore.getInstance()
        db.collection("order")
            .add(order)
            .addOnFailureListener {
                Log.d("mine", "Error upload order")
                throw Exception("Database upload", it)
            }
    }

    fun deleteSession(mac: String, callBack: DeleteSessionCallBack) {
        if (CurrentUser.sessionId.isBlank() || CurrentUser.sessionId.isEmpty()) {
            callBack.onCallBack()
            return
        }
        val db = FirebaseFirestore.getInstance()
        db.collection("session")
            .document(CurrentUser.sessionId)
            .delete()
            .addOnSuccessListener {
                callBack.onCallBack()
            }
            .addOnFailureListener {
                Log.d("mine", "Error upload order")
                throw Exception("Database upload", it)
            }
    }

    fun updateAccount(accountId: String, account: Account) {
        val db = FirebaseFirestore.getInstance()
        val map = HashMap<String, Any>()
        map["name"] = account.name
        db.collection("user").document(accountId).update(map)
    }

}