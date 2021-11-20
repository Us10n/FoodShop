package com.example.foodshop.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.foodshop.R
import com.example.foodshop.ShavaHolder
import com.example.foodshop.databinding.FragmentFullOfferBinding
import com.example.foodshop.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding
    private val holder = ShavaHolder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var orderText = ""
        var fullPrice: Double = 0.0
        holder.getSpecialList().forEach {
            orderText += it.item.name
            orderText += ":"
            if (it.item.description.isEmpty()) orderText += "classic"
            else orderText += it.item.description
            orderText += " - "
            orderText += it.number
            orderText += ";\n"
            fullPrice += it.item.price[0] * it.number
        }
        binding.price.text = "Price:" + fullPrice
        binding.bonus.text = "Bonus:" + fullPrice/5.0
        binding.sendOrder.setOnClickListener {
            if (!binding.name.text.isEmpty() && !binding.number.text.isEmpty()) {
                Toast.makeText(context, "Order was sent", Toast.LENGTH_LONG).show()
                holder.deleteAll()
                requireActivity().supportFragmentManager.popBackStack()
            } else {
                Toast.makeText(context, "Name or number is empty", Toast.LENGTH_LONG).show()
            }
        }
        binding.comment.text = orderText
        binding.toolBar.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

}