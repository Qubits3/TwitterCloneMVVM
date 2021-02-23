package com.example.twitterclonemvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twitterclonemvvm.R
import com.example.twitterclonemvvm.adapter.TweetAdapter
import com.example.twitterclonemvvm.databinding.FragmentFeedBinding
import com.example.twitterclonemvvm.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val tweetAdapter = TweetAdapter(arrayListOf())
    private lateinit var dataBinding: FragmentFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)

        tweetList.layoutManager = LinearLayoutManager(context)
        tweetList.adapter = tweetAdapter

        dataBinding.fab.setOnClickListener {
            arguments?.let {
                findNavController().navigate(
                    FeedFragmentDirections.actionFeedFragmentToTweetFragment(
                        SignInFragmentArgs.fromBundle(it).userUID
                    )
                )
            }

        }

        dataBinding.swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }

        viewModel.getUserFollowings("(userUID)DG468GS84FA6HGF4A")

//        findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToTweetFragment())

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.tweets.observe(viewLifecycleOwner, {
            it?.let {
                tweetAdapter.updateTweetList(it)
            }
        })
    }
}