package com.example.twitterclonemvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.twitterclonemvvm.R
import com.example.twitterclonemvvm.viewmodel.TweetViewModel

class TweetFragment : Fragment() {

    private lateinit var viewModel: TweetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tweet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TweetViewModel::class.java)

        viewModel.tweet("(userUID)DG468GS84FA6HGF4A")

    }
}