package com.example.foodshop.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodshop.MainActivity
import com.example.foodshop.R
import com.example.foodshop.ShavaApplication
import com.example.foodshop.ShavaHolder
import com.example.foodshop.listener.ShavaListener
import com.example.foodshop.adapters.MenuAdapter
import com.example.foodshop.adapters.MenuPositionAdapter
import com.example.foodshop.databinding.FragmentMenuBinding
import com.example.foodshop.listener.FullScreenListener
import com.example.foodshop.viewmodel.MenuFragmentViewModel
import com.example.foodshop.viewmodel.ViewModelFactory

class MenuFragment : Fragment(), ShavaListener, FullScreenListener {

    private val viewModel: MenuFragmentViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).getMyApplication() as ShavaApplication).repository)
    }
    private val holder = ShavaHolder
    private lateinit var binding: FragmentMenuBinding
    private val menuAdapter = MenuAdapter()
    private val menuPositionAdapter = MenuPositionAdapter(holder, this, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerForDiscounts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = menuAdapter
        }

        binding.recyclerForMenu.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = menuPositionAdapter
        }

        viewModel.positions.observe(viewLifecycleOwner,
            Observer { _ ->
                run {
                    val tmp = viewModel.positions.value;
                    Log.d("mine2", tmp.toString())
                    menuAdapter.submitList(viewModel.positions.value)
                    menuPositionAdapter.submitList(viewModel.positions.value)
                }

            })


        viewModel.loadMenuPositions()
    }

    override fun loadImage(url: String, view: ImageView) {
        viewModel.loadPosition(url, view)
    }

    override fun createFullScreen(url: String, name: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fContainerView, FullScreenFragment.newInstance(url, name))
            .addToBackStack("").commit()
    }
}