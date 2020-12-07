package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.core.KeyValues
import com.udacity.shoestore.core.extensions.showDialog
import com.udacity.shoestore.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import tech.jesselima.local.sharedpref.data.SharedPrefUserStorage


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModel<LoginViewModel>()

    private val sharedPrefUserStorage: SharedPrefUserStorage by inject()

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
        val isLoggedIn = sharedPrefUserStorage.getBooleanValue(KeyValues.KEY_IS_AUTHENTICATED) ?: false
        if(isLoggedIn) findNavController().navigate(R.id.navigateFromLoginToFeed)
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.isLoginSuccess.observe(viewLifecycleOwner, { isLoginSuccess ->
            val haveSeenWelcome = sharedPrefUserStorage.getBooleanValue(KeyValues.KEY_HAVE_SEEN_WELCOME) ?: false
            if (isLoginSuccess) {
                if (haveSeenWelcome) {
                    findNavController().navigate(R.id.navigateFromLoginToFeed)
                } else {
                    findNavController().navigate(R.id.navigateToWelcome)
                }
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
        })
        viewModel.hasSampleData.observe(viewLifecycleOwner, { hasSampleData ->
            buttonInsertShoes.isVisible = hasSampleData.not()
        })
        viewModel.loginError.observe(viewLifecycleOwner, { errorMessage ->
            showLoginDialog(message = errorMessage)
        })
    }

    private fun setupListeners() {
        binding.buttonActionLogin.setOnClickListener {
            val email = loginEditTextUserName.text.toString()
            val password = editTextPassword.text.toString()
            if (validateForm(email, password)) {
                viewModel.authenticateUser(email, password)
            } else {
                showLoginDialog(getString(R.string.message_login_form_error))
            }

        }
        binding.buttonInsertShoes.setOnClickListener {
            it.visibility = View.GONE
            viewModel.insertSampleShoes()
        }
    }

    private fun validateForm(email: String?, password: String?): Boolean {
        return email.isNullOrEmpty().not() && password.isNullOrBlank().not()
    }

    private fun showLoginDialog(message: String) {
        context?.let {
            showDialog(
                context = it,
                message = message
            )
        }
    }
}