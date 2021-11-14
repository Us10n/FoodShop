package com.example.foodshop.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import com.example.foodshop.Repository
import com.example.foodshop.callback.AccountCallBack
import com.example.foodshop.database.Account
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountFragmentViewModel(private val repository: Repository) : ViewModel() {
    private val liveAccount: MutableLiveData<Account> = MutableLiveData()
    val account: LiveData<Account>
        get() = liveAccount

    fun loadImage(url: String, view: ImageView) {
        repository.loadImage(url, view)
    }

    fun initTabMediator(tabLayout: TabLayout, vPager: ViewPager2) {
        TabLayoutMediator(tabLayout, vPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Returns"
                }
                1 -> {
                    tab.text = "Order History"
                }
            }
        }.attach()
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
}