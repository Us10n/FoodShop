package com.example.foodshop

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodshop.databinding.ActivityMainBinding
import com.example.foodshop.fragment.MenuFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) openMenuFragment()
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        binding.cart.setOnClickListener(){
            openCartFragment()
        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu -> openMenuFragment()
                R.id.offers -> openOffersFragment()
                R.id.coupons -> openCouponsFragment()
               R.id.account -> openAccountFragment()
            }
            true
        }
    }

    private fun openAccountFragment() {
        Toast.makeText(
            this,
            "AccountFragment :3",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun openCouponsFragment() {
        Toast.makeText(
            this,
            "CouponsFragment :3",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun openCartFragment() {
        Toast.makeText(
            this,
            "CartFragment :3",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun openOffersFragment() {
        Toast.makeText(
            this,
            "OffersFragment :3",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun openMenuFragment() {
        val recyclerFragment = MenuFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id , recyclerFragment).commit()
    }


}