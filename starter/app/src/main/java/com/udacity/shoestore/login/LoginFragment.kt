package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.isLoginSuccess.observe(viewLifecycleOwner, { isLoginSuccess ->
            if (isLoginSuccess) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                loginLayout.visibility = View.GONE
                loginLoadingIndicator.visibility = View.VISIBLE
                loginLoadingIndicator.playAnimation()
            } else {
                loginLoadingIndicator.visibility = View.GONE
                loginLoadingIndicator.cancelAnimation()
            }
        })
        viewModel.isSavingSampleDataLiveData.observe(viewLifecycleOwner, { isSavingSampleData ->
            saveSampleDataLoading.isVisible = isSavingSampleData
            if (isSavingSampleData.not()) {
                Toast.makeText(context, getString(R.string.message_sample_data_saved), Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.hasSampleAlreadyDataLiveData.observe(viewLifecycleOwner, { hasSampleData ->
            buttonInsertShoes.isVisible = hasSampleData.not()
        })
    }

    private fun setupListeners() {
        binding.buttonActionLogin.setOnClickListener {
            viewModel.authenticateUser(email = "", password = "")
        }
        binding.buttonInsertShoes.setOnClickListener {
            it.visibility = View.GONE
            viewModel.insertSampleShoes()
        }
    }
}