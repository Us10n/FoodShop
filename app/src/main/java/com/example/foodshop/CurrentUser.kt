package com.example.foodshop

import com.example.foodshop.database.Account

object CurrentUser {
    val account by lazy { Account() }
    var sessionId: String = ""
    var accountId:String=""
}
