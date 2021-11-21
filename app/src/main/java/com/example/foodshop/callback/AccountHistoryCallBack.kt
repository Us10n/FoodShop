package com.example.foodshop.callback

import com.example.foodshop.recycler.HistoryPosition

interface AccountHistoryCallBack {
    fun onCallback(historyList: List<HistoryPosition>)
}