package com.example.foodshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodshop.MainActivity
import com.example.foodshop.R
import com.example.foodshop.ShavaApplication
import com.example.foodshop.ShavaHolder
import com.example.foodshop.adapters.OfferAdapter
import com.example.foodshop.databinding.FragmentOffersBinding
import com.example.foodshop.listener.FullScreenListener
import com.example.foodshop.listener.ShavaListener
import com.example.foodshop.viewmodel.OffersFragmentViewModel
import com.example.foodshop.viewmodel.ViewModelFactory

class OffersFragment : Fragment(), ShavaListener, FullScreenListener {

    private val viewModel: OffersFragmentViewModel by viewModels {
        ViewModelFactory(((activity as MainActivity).getMyApplication() as ShavaApplication).repository)
    }
    private lateinit var binding: FragmentOffersBinding
    private val holder = ShavaHolder
    private val offerAdapter = OfferAdapter(holder, this, this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOffersBinding.inflate(inflater)
        binding.offerRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = offerAdapter
        }
        viewModel.offers.observe(viewLifecycleOwner, Observer {
            offerAdapter.submitList(viewModel.offers.value)
        })

        viewModel.loadOffers()
        return binding.root
    }

    override fun loadImage(url: String, view: ImageView) {
        viewModel.loadPosition(url, view)
    }

    override fun createFullScreen(url: String, name: String) {
        TODO("Not yet implemented")
    }

    override fun createFullScreen(url: String, name: String, description:String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fContainerView, FullOfferFragment.newInstance(url, name, description))
            .addToBackStack("").commit()
    }

}
