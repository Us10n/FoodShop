package com.example.foodshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.foodshop.R
import com.example.foodshop.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, recyclerFragment).commit()
    }

    private fun openCouponsFragment() {
        Toast.makeText(
            requireContext(),
            "CouponsFragment :3",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun openCartFragment() {
        val recyclerFragment = ShoppingCartFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, recyclerFragment).commit()
    }

    private fun openOffersFragment() {
        val recyclerFragment = OffersFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, recyclerFragment).commit()
    }

    private fun openMenuFragment() {
        val recyclerFragment = MenuFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(binding.fContainerView.id, recyclerFragment).commit()
    }
}