package com.example.foodshop.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.foodshop.CurrentUser
import com.example.foodshop.MainActivity
import com.example.foodshop.ShavaApplication
import com.example.foodshop.ShavaHolder
import com.example.foodshop.databinding.FragmentOrderBinding
import com.example.foodshop.recycler.HistoryPosition
import com.example.foodshop.viewmodel.OrderFragmentViewModel
import com.example.foodshop.viewmodel.ViewModelFactory

class OrderFragment : Fragment() {

    private val viewModel: OrderFragmentViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).getMyApplication() as ShavaApplication).repository)
    }
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
        val cats = arrayOf("Гикало, 9", "Петруся Бровки, 6")

        val adapter = ArrayAdapter<String>(
            requireContext(), R.layout.simple_dropdown_item_1line, cats
        )
        binding.autoCompleteTextView.apply {
            setAdapter(adapter)
            threshold = 2
            onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.autoCompleteTextView.showDropDown()
                }
            }
        }
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
        binding.bonus.text = "Bonus:" + fullPrice / 5.0
        binding.name.setText(CurrentUser.account.name)
        binding.number.setText(CurrentUser.account.phone)
        binding.sendOrder.setOnClickListener {
            if (!binding.name.text.isEmpty() && !binding.number.text.isEmpty()) {
                val order = HistoryPosition(
                    CurrentUser.accountId,
                    (fullPrice / 5.0).toString(),
                    binding.comment.text.toString()
                )
                viewModel.addOrderToHistory(order)
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