package com.example.twitterclonemvvm.services

import com.example.twitterclonemvvm.model.Tweet
import com.example.twitterclonemvvm.util.Util
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TweetAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(Util.POST_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(TweetAPI::class.java)

    fun sendTweet(tweet: Tweet, userUID: String?): Single<JsonObject> {
        return api.sendTweet(tweet, userUID)
    }

    fun getTweets(userUID: String?) : Observable<JsonObject> {
        return api.getTweets(userUID)
    }

}