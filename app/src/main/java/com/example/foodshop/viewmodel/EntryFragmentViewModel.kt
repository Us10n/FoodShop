package com.example.foodshop.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodshop.MainActivity
import com.example.foodshop.Repository
import com.example.foodshop.callback.AuthCallBack
import com.example.foodshop.callback.DeviceStatusCallBack
import com.example.foodshop.database.Account
import com.example.foodshop.managers.VerificationManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntryFragmentViewModel(private val repository: Repository) : ViewModel() {

    lateinit var manager: VerificationManager

    private val isSignSuccessfulLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isSignSuccessful: LiveData<Boolean>
        get() = isSignSuccessfulLiveData
    private val isAuthorizedLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isAuthorized: LiveData<Boolean>
        get() = isAuthorizedLiveData

    fun initManager(mainActivity: MainActivity) {
        manager = VerificationManager(mainActivity)
    }

    fun startPhoneNumberVerification(number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.startPhoneNumberVerification(number)
        }
    }

    fun verifyPhoneNumberWithCode(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.verifyPhoneNumberWithCode(code, object : AuthCallBack {
                override fun onCallback(isSigned: Boolean) {
                    isSignSuccessfulLiveData.postValue(isSigned)
                }
            })
        }
    }

    fun getAuthorizationStatus(mac: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadDeviceAuthStatus(mac, object : DeviceStatusCallBack {
                override fun onCallback(isAuthorized: Boolean) {
                    isAuthorizedLiveData.postValue(isAuthorized)
                }
            })
        }
    }

    fun getDeviceMacAddress(context: Context): String {
        return repository.getDeviceMacAddress(context)
    }

    fun addSession(mac: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSession(mac)
        }
    }

    fun addUser(account: Account) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAccount(account)
        }
    }
}