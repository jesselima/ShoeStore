package com.udacity.shoestore.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome,
            container,
            false
        )
        setupViewModel()
        setupObservers()
        setupListeners()
        return binding.root
    }

    private fun setupViewModel() {
        // TODO("not implemented")
    }

    private fun setupObservers() {
        // TODO("not implemented")
    }

    private fun setupListeners() {
        binding.buttonActionFromWelcome.setOnClickListener {
            findNavController().navigate(
                WelcomeFragmentDirections.navigateToInstructions()
            )
        }
    }
}