package com.example.foodshop

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodshop.databinding.ActivityMainBinding
import com.example.foodshop.fragment.AccountFragment
import com.example.foodshop.fragment.MenuFragment
import com.example.foodshop.fragment.ShoppingCartFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) openMenuFragment()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu -> openMenuFragment()
                R.id.offers -> openOffersFragment()
                R.id.coupons -> openCouponsFragment()
                R.id.account -> openAccountFragment()
                R.id.cart -> openCartFragment()
            }
            true
        }
    }

    private fun openAccountFragment() {
        val recyclerFragment = AccountFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, recyclerFragment).commit()
    }

    private fun openCouponsFragment() {
        Toast.makeText(
            this,
            "CouponsFragment :3",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun openCartFragment() {
        val recyclerFragment = ShoppingCartFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, recyclerFragment).commit()
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
        transaction.replace(binding.fContainerView.id, recyclerFragment).commit()
    }

    fun changeTitle(title: String) {
        binding.title.text = title
    }


}
