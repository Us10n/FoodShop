package com.example.foodshop

import android.content.Context
import android.widget.ImageView
import com.example.foodshop.callback.*
import com.example.foodshop.database.Account
import com.example.foodshop.database.Database
import com.example.foodshop.recycler.HistoryPosition
import java.net.NetworkInterface
import java.util.*

class Repository {
    private val database = Database()

    fun loadImage(url: String, view: ImageView) {
        database.loadImgByUrl(url, view)
    }

    fun loadOffers(myCallBack: OfferCallBack) {
        return database.loadOffers(myCallBack)
    }

    fun loadMenuPositions(myCallBack: MenuCallBack) {
        return database.loadMenuPositions(myCallBack)
    }

    fun loadAccount(mac: String, myCallBack: AccountCallBack) {
        return database.loadAccount(mac, myCallBack)
    }

    fun loadAccountHistory(mac: String, myCallBack: AccountHistoryCallBack) {
        database.loadAccountHistory(mac, myCallBack)
    }

    fun addSession(mac: String) {
        return database.addSession(mac)
    }

    fun loadDeviceAuthStatus(mac: String, myCallBack: DeviceStatusCallBack) {
        return database.loadDeviceAuthStatus(mac, myCallBack)
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

    fun addAccount(account: Account) {
        database.uploadAccount(account)
    }

    fun addOrder(order: HistoryPosition) {
        database.uploadOrder(order)
    }

    fun deleteSession(mac: String, callBack: DeleteSessionCallBack) {
        database.deleteSession(mac,callBack)
    }
}