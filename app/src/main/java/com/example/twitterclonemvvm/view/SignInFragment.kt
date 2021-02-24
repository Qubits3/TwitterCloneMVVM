package com.example.twitterclonemvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.twitterclonemvvm.R
import com.example.twitterclonemvvm.databinding.FragmentSignInBinding
import com.example.twitterclonemvvm.model.Auth
import com.example.twitterclonemvvm.services.AuthAPIService
import com.example.twitterclonemvvm.viewmodel.SignInViewModel

class SignInFragment : Fragment() {

    private lateinit var viewModel: SignInViewModel
    private lateinit var dataBinding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)

        dataBinding.signInTextView.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }

        dataBinding.signInButton.setOnClickListener {
            viewModel.signIn(view,Auth(dataBinding.signInEmailEditText.text.toString(), dataBinding.signInPasswordEditText.text.toString()))
        }

//        findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToFeedFragment())

    }
}