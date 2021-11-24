package com.example.foodshop.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodshop.CurrentUser
import com.example.foodshop.MainActivity
import com.example.foodshop.ShavaApplication
import com.example.foodshop.adapters.HistoryAdapter
import com.example.foodshop.callback.DeleteSessionCallBack
import com.example.foodshop.databinding.CodeWindowBinding
import com.example.foodshop.databinding.FragmentAccountBinding
import com.example.foodshop.viewmodel.AccountFragmentViewModel
import com.example.foodshop.viewmodel.ViewModelFactory

class AccountFragment : Fragment() {

    private val viewModel: AccountFragmentViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).getMyApplication() as ShavaApplication).repository)
    }
    private lateinit var binding: FragmentAccountBinding
    private val historyAdapter = HistoryAdapter()
    private lateinit var codeBinding: CodeWindowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater)
        binding.orderHistoryRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }
        viewModel.account.observe(viewLifecycleOwner, Observer {
            CurrentUser.account.phone = it.phone
            CurrentUser.account.name = it.name
            binding.accountName.text = it.name
            binding.accountPhoneNumber.text = it.phone
            viewModel.loadImage(it.imgUrl, binding.accimage)
        })
        viewModel.history.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                historyAdapter.items = it
            }
        })
        binding.editImg.setOnClickListener {
            openNameWindow()
        }
        binding.exitImg.setOnClickListener {
            deleteSession()
        }
        loadAccountInfo()
        loadAccountHistory()
        return binding.root
    }

    private fun deleteSession() {
        viewModel.deleteSession(CurrentUser.account.mac, object : DeleteSessionCallBack {
            override fun onCallBack() {
                openEntryFragment()
            }
        })

    }

    private fun loadAccountInfo() {
        viewModel.loadAccount(CurrentUser.account.mac)
    }

    private fun loadAccountHistory() {
        viewModel.loadAccountHistory(CurrentUser.account.mac)
    }

    private fun openNameWindow() {
        val codeDialog = AlertDialog.Builder(requireContext())
            .setTitle("Name")
            .setMessage("Edit")
        codeBinding = CodeWindowBinding.inflate(LayoutInflater.from(requireContext()))
        codeBinding.codeField.hint = binding.accountName.text.toString()
        codeBinding.codeField.inputType = InputType.TYPE_CLASS_TEXT
        codeDialog.setView(codeBinding.root)
        codeDialog.setPositiveButton("Ok")
        { _, _ ->
            if (codeBinding.codeField.text.toString().isNotBlank()) {
                binding.accountName.text = codeBinding.codeField.text
            } else {
                Toast.makeText(requireContext(), "field is empty", Toast.LENGTH_LONG).show()
            }
        }
        codeDialog.setNegativeButton("Back")
        { _, _ -> (activity as MainActivity).supportFragmentManager.popBackStack() }

        (activity as MainActivity).supportFragmentManager.popBackStack()
        codeDialog.show()
    }

    private fun openEntryFragment() {
        (activity as MainActivity).openEntryFragment()
    }
}