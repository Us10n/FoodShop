package com.example.foodshop.managers

import android.util.Log
import com.example.foodshop.CurrentUser
import com.example.foodshop.MainActivity
import com.example.foodshop.callback.AuthCallBack
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class VerificationManager(
    private val mainActivity: MainActivity
) {
    private var forceResendingToken: PhoneAuthProvider.ForceResendingToken? = null
    private var authCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var verificationId: String
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        authCallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(
                verId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verId, token)
                Log.d("mine", "onCodeSent $verId")
                forceResendingToken = token
                verificationId = verId
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
//                signInWithPhoneAuthCredential(phoneAuthCredential, null)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // что-то в этом случае надо сделать
                Log.d("mine", "Ver failed $e")

            }
        }
    }

    fun startPhoneNumberVerification(number: String) {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(number)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(mainActivity)
            .setCallbacks(authCallBacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun resendVerificationCode(
        number: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(number)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(mainActivity)
            .setCallbacks(authCallBacks)
            .setForceResendingToken(token)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyPhoneNumberWithCode(code: String, authCallBack: AuthCallBack) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential, authCallBack)
    }

    private fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        authCallBack: AuthCallBack?
    ) {
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                val phone = firebaseAuth.currentUser?.phoneNumber
                CurrentUser.account.phone = phone ?: ""
                authCallBack?.onCallback(true)
            }
            .addOnFailureListener { e ->
                Log.d("mine", "sign in with auth cred $e")
                authCallBack?.onCallback(false)
            }
    }

}