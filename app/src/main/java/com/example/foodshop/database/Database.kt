package com.example.foodshop.database

import android.widget.ImageView
import com.squareup.picasso.Picasso

class Database {

    fun loadImgByUrl(url: String, view: ImageView) {
        Picasso.get()
            .load(url)
            .into(view)
    }

}