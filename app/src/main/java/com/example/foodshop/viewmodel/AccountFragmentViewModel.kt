package com.example.foodshop.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodshop.Repository
import com.example.foodshop.callback.AccountCallBack
import com.example.foodshop.callback.AccountHistoryCallBack
import com.example.foodshop.callback.DeleteSessionCallBack
import com.example.foodshop.database.Account
import com.example.foodshop.recycler.HistoryPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountFragmentViewModel(private val repository: Repository) : ViewModel() {
    private val liveAccount: MutableLiveData<Account> = MutableLiveData()
    val account: LiveData<Account>
        get() = liveAccount
    private val liveAccountHistoryData: MutableLiveData<List<HistoryPosition>> = MutableLiveData()
    val history: LiveData<List<HistoryPosition>>
        get() = liveAccountHistoryData


    fun loadImage(url: String, view: ImageView) {
        repository.loadImage(url, view)
    }

    fun loadAccount(uid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadAccount(uid, object : AccountCallBack {
                override fun onCallBack(account: Account) {
                    liveAccount.postValue(account)
                }
            })
        }
    }

    fun loadAccountHistory(mac: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadAccountHistory(mac, object : AccountHistoryCallBack {
                override fun onCallback(historyList: List<HistoryPosition>) {
                    liveAccountHistoryData.postValue(historyList)
                }
            })
        }
    }

    fun deleteSession(mac: String, callBack: DeleteSessionCallBack) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSession(mac,callBack)
        }
    }
}