package com.example.foodshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.foodshop.CurrentUser
import com.example.foodshop.MainActivity
import com.example.foodshop.R
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
        binding.accountPhoneNumber.text =
            if (CurrentUser.number.isNotEmpty()) CurrentUser.number else
                getString(R.string.emptyPhoneNumber)
        viewModel.initTabMediator(binding.tabLayout, binding.vPager)
        viewModel.account.observe(viewLifecycleOwner, Observer {
            binding.accountName.text=it.name
            binding.accountPhoneNumber.text=it.phone
            viewModel.loadImage(it.imgUrl,binding.accimage)
        })
        viewModel.loadAccount(CurrentUser.uid)
        return binding.root
    }

}