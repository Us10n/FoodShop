package com.example.foodshop.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.foodshop.CurrentUser
import com.example.foodshop.MainActivity
import com.example.foodshop.ShavaApplication
import com.example.foodshop.databinding.AutificationBinding
import com.example.foodshop.databinding.CodeWindowBinding
import com.example.foodshop.databinding.RegistrationWindowBinding
import com.example.foodshop.viewmodel.EntryFragmentViewModel
import com.example.foodshop.viewmodel.ViewModelFactory

class EntryFragment : Fragment() {

    private val viewModel: EntryFragmentViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).getMyApplication() as ShavaApplication).repository)
    }
    private lateinit var binding: AutificationBinding
    private lateinit var regBinding: RegistrationWindowBinding
    private lateinit var codeBinding: CodeWindowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AutificationBinding.inflate(inflater)

        val macAddress = viewModel.getDeviceMacAddress(requireContext())
        CurrentUser.account.mac = macAddress
        viewModel.isAuthorized.observe(viewLifecycleOwner, Observer {
            if (it) {
                openMainFragment()
            }
        })
        viewModel.getAuthorizationStatus(macAddress)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.isSignSuccessful.observe(viewLifecycleOwner) {
            if (it) {
                addSession(CurrentUser.account.mac)
                addUser()
                openMainFragment()
            } else Toast.makeText(requireContext(), "verification failed", Toast.LENGTH_LONG)
                .show()
        }
        binding.signIn.setOnClickListener {
            openMainFragment()
        }
        viewModel.initManager(activity as MainActivity)
        binding.registration.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext())
                .setTitle("Registration")
                .setMessage("Fill all fields")
            val inflater = LayoutInflater.from(requireContext())
            regBinding = RegistrationWindowBinding.inflate(inflater)
            dialog.setView(regBinding.root)
            dialog.setPositiveButton("Ok")
            { _, _ ->
                if (regBinding.numberField.text.toString().isNotBlank()) {
                    viewModel.startPhoneNumberVerification(regBinding.numberField.text.toString())
                    openCodeWindow()
                } else {
                    Toast.makeText(requireContext(), "field is empty", Toast.LENGTH_LONG).show()
                }
            }
            dialog.setNegativeButton("Back")
            { _, _ -> (activity as MainActivity).supportFragmentManager.popBackStack() }
            dialog.show()
        }
    }

    private fun openCodeWindow() {
        val codeDialog = AlertDialog.Builder(requireContext())
            .setTitle("Registration")
            .setMessage("Enter the code")
        codeBinding = CodeWindowBinding.inflate(LayoutInflater.from(requireContext()))
        codeDialog.setView(codeBinding.root)
        codeDialog.setPositiveButton("Ok")
        { _, _ ->
            if (codeBinding.codeField.text.toString().isNotBlank()) {
                viewModel.verifyPhoneNumberWithCode(codeBinding.codeField.text.toString())
            } else {
                Toast.makeText(requireContext(), "wrong code", Toast.LENGTH_LONG).show()
            }
        }
        codeDialog.setNegativeButton("Back")
        { _, _ -> (activity as MainActivity).supportFragmentManager.popBackStack() }

        (activity as MainActivity).supportFragmentManager.popBackStack()
        codeDialog.show()
    }

    private fun openMainFragment() {
        (activity as MainActivity).openMainFragment()
    }

    private fun addSession(mac: String) {
        viewModel.addSession(mac)
    }

    private fun addUser() {
        viewModel.addUser(CurrentUser.account)
    }
}