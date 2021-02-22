package com.example.twitterclonemvvm.services

import com.example.twitterclonemvvm.model.Tweet
import com.example.twitterclonemvvm.util.Util
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TweetAPIService {

    private var api = Retrofit.Builder()
        .baseUrl(Util.POST_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(TweetAPI::class.java)

    fun tweet(tweet: Tweet, userUID: String?): Single<JsonObject> {
        return api.tweet(tweet, userUID, Util.POST_KEY)
    }

}