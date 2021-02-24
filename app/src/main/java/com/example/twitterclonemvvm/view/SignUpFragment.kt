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
import com.example.twitterclonemvvm.databinding.FragmentSignUpBinding
import com.example.twitterclonemvvm.model.Auth
import com.example.twitterclonemvvm.model.UserInfo
import com.example.twitterclonemvvm.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel
    private lateinit var dataBinding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)

        dataBinding.signUpButton.setOnClickListener {
            viewModel.signUp(
                view,
                Auth(
                    dataBinding.signUpEmailEditText.text.toString(),
                    dataBinding.signUpPasswordEditText.text.toString()
                ), dataBinding.signUpUsernameEditText.text.toString(),
                "",
                ""
            )
        }

    }
}