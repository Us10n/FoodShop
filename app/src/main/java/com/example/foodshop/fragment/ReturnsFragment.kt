package com.example.foodshop.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodshop.R
import com.example.foodshop.databinding.FragmentRuturnsBinding

class ReturnsFragment : Fragment() {

    private lateinit var binding: FragmentRuturnsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRuturnsBinding.inflate(layoutInflater)
        return binding.root
    }


}