package com.example.foodshop

import android.widget.ImageView
import com.example.foodshop.callback.DbCallBack
import com.example.foodshop.database.Database
import com.example.foodshop.recycler.FoodPosition

class Repository {
    private val database = Database()

    fun loadImage(url: String, view: ImageView) {
        database.loadImgByUrl(url, view)
    }

    suspend fun loadMenuPositions(myCallBack: DbCallBack): List<FoodPosition> {
        return database.loadMenuPositions(myCallBack);
    }
}