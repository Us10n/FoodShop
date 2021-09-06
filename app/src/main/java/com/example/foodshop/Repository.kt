package com.example.foodshop

import android.widget.ImageView
import com.example.foodshop.database.Database

class Repository {
    private val database = Database()

    fun loadImage(url: String, view: ImageView) {
        database.loadImgByUrl(url, view)
    }
}