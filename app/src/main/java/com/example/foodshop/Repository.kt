package com.example.foodshop

import android.widget.ImageView
import com.example.foodshop.database.Database
import com.example.foodshop.recycler.MenuPosition

class Repository {
    private val database = Database()

    fun loadImage(url: String, view: ImageView) {
        database.loadImgByUrl(url, view)
    }

    suspend fun loadMenuPostions(): List<MenuPosition> {
        return database.loadMenuPositions();
    }

    suspend fun uploadMenuPosition(position: MenuPosition){
        database.uploadMenuPosition(position)
    }
}