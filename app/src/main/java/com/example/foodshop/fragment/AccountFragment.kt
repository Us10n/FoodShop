package com.example.foodshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.example.foodshop.MainActivity
import com.example.foodshop.ShavaApplication
import com.example.foodshop.adapters.AccountTabAdapter
import com.example.foodshop.databinding.FragmentAccountBinding
import com.example.foodshop.viewmodel.AccountFragmentViewModel
import com.example.foodshop.viewmodel.ViewModelFactory

class AccountFragment : Fragment() {

    private val viewModel: AccountFragmentViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).getMyApplication() as ShavaApplication).repository)
    }
    lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater)
        binding.vPager.adapter = AccountTabAdapter(activity as FragmentActivity)
        viewModel.loadImage(
            "https://firebasestorage.googleapis.com/v0/b/test-4c0c2.appspot.com/o/imgs%2Ftest1.png?alt=media&token=6c3bfcbd-d8f2-4d63-aae9-34d6d84ef468",
            binding.accimage
        )

        viewModel.initTabMediator(binding.tabLayout, binding.vPager)

        return binding.root
    }

}