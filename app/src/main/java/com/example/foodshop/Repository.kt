package com.example.foodshop

import android.content.Context
import android.widget.ImageView
import com.example.foodshop.callback.AccountCallBack
import com.example.foodshop.callback.MenuCallBack
import com.example.foodshop.callback.OfferCallBack
import com.example.foodshop.database.Database
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import java.net.NetworkInterface
import java.util.*

class Repository {
    private val database = Database()

    fun loadImage(url: String, view: ImageView) {
        database.loadImgByUrl(url, view)
    }

    fun loadOffers(myCallBack: OfferCallBack){
        return database.loadOffers(myCallBack)
    }

    fun loadMenuPositions(myCallBack: MenuCallBack){
        return database.loadMenuPositions(myCallBack)
    }

    fun loadAccount(uid:String, myCallBack: AccountCallBack){
        return database.loadAccount(uid,myCallBack)
    }

    fun getDeviceMacAddress(context: Context): String {
        val networkInterfaceList: List<NetworkInterface> =
            Collections.list(NetworkInterface.getNetworkInterfaces())
        var stringMac = ""
        for (networkInterface in networkInterfaceList) {
            if (networkInterface.name.equals("wlan0")) {
                for (element in networkInterface.hardwareAddress) {
                    var stringMacByte =
                        Integer.toHexString((element.toUByte().and(0xFF.toUByte())).toInt())
                    if (stringMacByte.length == 1) {
                        stringMacByte = "0$stringMacByte"
                    }
                    stringMac = stringMac + stringMacByte.uppercase(Locale.getDefault()) + ":"
                }
                break
            }
        }
        return stringMac.dropLast(1)
    }
}