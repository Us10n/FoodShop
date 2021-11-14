package com.example.foodshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.foodshop.MainActivity
import com.example.foodshop.ShavaApplication
import com.example.foodshop.ShavaHolder
import com.example.foodshop.databinding.FullScreenFragmentBinding
import com.example.foodshop.database.FoodPosition
import com.example.foodshop.viewmodel.FullScreenViewModel
import com.example.foodshop.viewmodel.ViewModelFactory

class FullScreenFragment : Fragment() {

    private val viewModel: FullScreenViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).getMyApplication() as ShavaApplication).repository)
    }

    private lateinit var binding: FullScreenFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FullScreenFragmentBinding.inflate(inflater)
        return binding.root
    }

    private val holder = ShavaHolder
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = arguments?.getString(NAME).toString()
        viewModel.loadImage(arguments?.getString(IMAGE_URL).toString(), binding.image)

        binding.add.setOnClickListener {
            holder.addShava(
                FoodPosition(
                    0, arguments?.getString(NAME).toString(), "", createDescription(), listOf(1.0),
                    arrayListOf(1.0)
                )
            )
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.toolBar.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun createDescription(): String {
        var description = ""
        if (binding.firstCheckBox.isChecked) {
            description += binding.firstCheckBox.text.toString()
            description += ";"
        }
        if (binding.secondCheckBox.isChecked) {
            description += binding.secondCheckBox.text.toString()
            description += ";"
        }
        if (binding.thirdCheckBox.isChecked) {
            description += binding.thirdCheckBox.text.toString()
            description += ";"
        }
        return description
    }

    companion object {
        fun newInstance(url: String, name: String): FullScreenFragment =
            FullScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(IMAGE_URL, url)
                    putString(NAME, name)
                }
            }

        private const val IMAGE_URL = "IMAGE_URL"
        private const val NAME = "NAME"
    }

}