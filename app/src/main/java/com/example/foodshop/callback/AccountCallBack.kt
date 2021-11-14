package com.example.foodshop.callback

import com.example.foodshop.database.Account

interface AccountCallBack {
    fun onCallBack(account: Account)
}