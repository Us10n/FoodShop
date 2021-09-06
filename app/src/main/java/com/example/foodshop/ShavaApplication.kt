package com.example.foodshop

import android.app.Application

class ShavaApplication : Application() {
    val repository by lazy { Repository() }
}