package com.example.foodshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.foodshop.MainActivity
import com.example.foodshop.adapters.AccountTabAdapter
import com.example.foodshop.database.Database
import com.example.foodshop.databinding.FragmentAccountBinding
import com.google.android.material.tabs.TabLayoutMediator

class AccountFragment : Fragment() {

    lateinit var binding: FragmentAccountBinding
    private val db = Database()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater)
        db.loadImgByUrl(
            "https://firebasestorage.googleapis.com/v0/b/test-4c0c2.appspot.com/o/imgs%2Ftest1.png?alt=media&token=6c3bfcbd-d8f2-4d63-aae9-34d6d84ef468",
            binding.accimage
        )

        binding.vPager.adapter= AccountTabAdapter(activity as FragmentActivity)

        TabLayoutMediator(binding.tabLayout, binding.vPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Returns"
                }
                1 -> {
                    tab.text = "Order History"
                }
            }
        }.attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        mainActivity.changeTitle("Account")

    }


}