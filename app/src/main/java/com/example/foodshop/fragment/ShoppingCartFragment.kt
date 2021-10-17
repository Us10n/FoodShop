package com.example.foodshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodshop.ShavaHolder
import com.example.foodshop.adapters.CartAdapter
import com.example.foodshop.databinding.FragmentCartBinding
import com.example.foodshop.recycler.SimpleItemTouchHelperCallback

class ShoppingCartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val holder = ShavaHolder
    private val orderAdapter = CartAdapter()
    private val touchHelper = ItemTouchHelper(SimpleItemTouchHelperCallback(orderAdapter))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.orderRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = orderAdapter
        }
        orderAdapter.submitList(holder.getSpecialList())
        touchHelper.attachToRecyclerView(binding.orderRecycler)
    }

}