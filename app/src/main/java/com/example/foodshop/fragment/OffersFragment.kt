package com.example.foodshop.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.foodshop.MainActivity
import com.example.foodshop.ShavaApplication
import com.example.foodshop.database.Database
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
    ): View {
        binding = FragmentOffersBinding.inflate(inflater)
        binding.btnGet.setOnClickListener {
            viewModel.loadMenuPositions();
            Log.d("mine1", "load call")
        }
        binding.btnSend.setOnClickListener {
            viewModel.uploadMenuPosition(MenuPosition(1, "kek", "url", "plus"))
        }

        viewModel.positions.observe(viewLifecycleOwner,
            Observer { positions ->
                run {
                    var tmp = viewModel.positions.value;
                    Log.d("mine2", tmp.toString())
                }

            })

        return binding.root
    }

}