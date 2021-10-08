package com.example.foodshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodshop.MainActivity
import com.example.foodshop.databinding.AutificationBinding

class RegistrationFragment : Fragment() {

    private lateinit var binding: AutificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AutificationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.signIn.setOnClickListener {
            openMainFragment()
        }
    }
    private fun openMainFragment() {
        (activity as MainActivity).openMainFragment()
    }
}