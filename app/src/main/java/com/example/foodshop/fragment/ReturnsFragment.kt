package com.example.foodshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodshop.databinding.FragmentReturnsBinding

class ReturnsFragment : Fragment() {

    private lateinit var binding: FragmentReturnsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReturnsBinding.inflate(layoutInflater)

        return binding.root
    }


}