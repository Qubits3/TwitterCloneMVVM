package com.example.twitterclonemvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.twitterclonemvvm.R
import com.example.twitterclonemvvm.databinding.FragmentTweetBinding
import com.example.twitterclonemvvm.viewmodel.TweetViewModel

class TweetFragment : Fragment() {

    private lateinit var viewModel: TweetViewModel
    private lateinit var dataBinding: FragmentTweetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tweet, container, false)

        dataBinding.tweetButton.setOnClickListener {
            viewModel.sendTweet("dfsdsfdsfsdf")
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TweetViewModel::class.java)

//        viewModel.sendTweet("(userUID)DG468GS84FA6HGF4A")

    }

}