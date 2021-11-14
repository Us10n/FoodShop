package com.example.foodshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodshop.MainActivity
import com.example.foodshop.Repository
import com.example.foodshop.callback.AuthCallBack
import com.example.foodshop.managers.VerificationManager

class EntryFragmentViewModel(private val repository: Repository) : ViewModel() {

    lateinit var manager: VerificationManager
    var isSignSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    fun initManager(mainActivity: MainActivity) {
        manager = VerificationManager(mainActivity)
    }

    fun startPhoneNumberVerification(number: String) {
        manager.startPhoneNumberVerification(number)
    }

    fun verifyPhoneNumberWithCode(code: String): Boolean {
        return manager.verifyPhoneNumberWithCode(code, object : AuthCallBack {
            override fun onCallback(isSigned: Boolean) {
                isSignSuccessful.postValue(isSigned)
            }
        })
    }
}