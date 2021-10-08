package com.example.foodshop

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodshop.databinding.ActivityMainBinding
import com.example.foodshop.fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) openRegistrationFragment()
    }

    private fun openRegistrationFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, RegistrationFragment()).commit()
    }

    fun openMainFragment() {
        val mainFragment = MainFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, mainFragment).commit()
    }

    fun getMyApplication(): Application? {
        return application
    }

}
