package com.example.twitterclonemvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.twitterclonemvvm.R
import com.example.twitterclonemvvm.databinding.ItemTweetBinding

class TweetAdapter : RecyclerView.Adapter<TweetAdapter.TweetViewHolder>() {

    class TweetViewHolder(var view: ItemTweetBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemTweetBinding>(inflater, R.layout.item_tweet, parent, false)

        return TweetViewHolder(view)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}