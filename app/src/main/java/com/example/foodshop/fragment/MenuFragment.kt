package com.example.foodshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodshop.R
import com.example.foodshop.databinding.FragmentMenuBinding
import com.example.foodshop.recycler.MenuAdapter
import com.example.foodshop.recycler.MenuPosition
import com.example.foodshop.recycler.MenuPositionAdapter

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val menuAdapter = MenuAdapter()
    private val menuPositionAdapter = MenuPositionAdapter()
    private val list = mutableListOf<MenuPosition>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerForDescounts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = menuAdapter
        }
        binding.recyclerForMenu.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = menuPositionAdapter
        }
        list.add(MenuPosition("Shava 1"))
        list.add(MenuPosition("Shava 2"))
        list.add(MenuPosition("Shava 3"))
        list.add(MenuPosition("Shava 4"))
        menuAdapter.submitList(list)
        menuPositionAdapter.submitList(list)

    }
}