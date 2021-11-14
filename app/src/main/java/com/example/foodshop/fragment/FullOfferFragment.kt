package com.example.foodshop.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.foodshop.MainActivity
import com.example.foodshop.R
import com.example.foodshop.ShavaApplication
import com.example.foodshop.database.FoodPosition
import com.example.foodshop.databinding.FragmentFullOfferBinding
import com.example.foodshop.databinding.FullScreenFragmentBinding
import com.example.foodshop.viewmodel.FullOfferViewModel
import com.example.foodshop.viewmodel.FullScreenViewModel
import com.example.foodshop.viewmodel.ViewModelFactory

class FullOfferFragment : Fragment() {

    private val viewModel: FullOfferViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).getMyApplication() as ShavaApplication).repository)
    }
    private lateinit var binding: FragmentFullOfferBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFullOfferBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = arguments?.getString(NAME).toString()
        viewModel.loadImage(arguments?.getString(IMAGE_URL).toString(), binding.image)
        binding.comment.text = arguments?.getString(DESCRIPTION).toString()
        binding.toolBar.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    companion object {

        fun newInstance(url: String, name: String, description: String) =
            FullOfferFragment().apply {
                arguments = Bundle().apply {
                    putString(IMAGE_URL, url)
                    putString(NAME, name)
                    putString(DESCRIPTION, description)
                }
            }

        private const val IMAGE_URL = "IMAGE_URL"
        private const val NAME = "NAME"
        private const val DESCRIPTION = "DESCRIPTION"
    }
}