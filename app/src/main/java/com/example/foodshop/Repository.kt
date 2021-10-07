package com.example.foodshop

import android.widget.ImageView
import com.example.foodshop.database.Database
import com.example.foodshop.recycler.MenuPosition

class Repository {
    private val database = Database()

    fun loadImage(url: String, view: ImageView) {
        database.loadImgByUrl(url, view)
    }

    suspend fun loadMenuPositions(myCallBack: MyCallBack): List<MenuPosition> {
        return database.loadMenuPositions(myCallBack);
    }

    suspend fun uploadMenuPosition(position: MenuPosition){
        database.uploadMenuPosition(position)
    }
}