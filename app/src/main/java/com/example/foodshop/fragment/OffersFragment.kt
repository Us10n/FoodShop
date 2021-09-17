package com.example.foodshop.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.foodshop.MainActivity
import com.example.foodshop.ShavaApplication
import com.example.foodshop.databinding.FragmentOffersBinding
import com.example.foodshop.recycler.MenuPosition
import com.example.foodshop.viewmodel.OffersFragmentViewModel
import com.example.foodshop.viewmodel.ViewModelFactory

class OffersFragment : Fragment() {

    private val viewModel: OffersFragmentViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).getMyApplication() as ShavaApplication).repository)
    }
    private lateinit var binding: FragmentOffersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOffersBinding.inflate(inflater)
        binding.btnGet.setOnClickListener {
            var tmp=viewModel.loadMenuPositions();
            Log.d("mine1", tmp.toString())
        }
        binding.btnSend.setOnClickListener {
            viewModel.uploadMenuPosition(MenuPosition(1, "kek", "url", "plus"))
        }
        return binding.root
    }

}