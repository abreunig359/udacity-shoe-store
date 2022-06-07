package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.lifecycleOwner = this
        setOnClickListeners()
        return binding.root
    }

    private fun setOnClickListeners() {
        val navigateToWelcome: (View) -> Unit = {
            viewModel.onLoginSuccessful()
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
            )
        }
        binding.singIn.setOnClickListener(navigateToWelcome)
        binding.signUp.setOnClickListener(navigateToWelcome)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("onDestroyView")
    }
}