package com.example.twitterclonemvvm.services

import com.example.twitterclonemvvm.model.Tweet
import com.example.twitterclonemvvm.util.Util
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface TweetAPI {

    @POST("tweets/{userUID}.json")
    fun sendTweet(
        @Body tweet: Tweet,
        @Path("userUID") userUID: String?,
        @Query("auth") key: String = Util.POST_KEY
    ): Single<JsonObject>

    @GET("tweets/{userUID}.json")
    fun getTweets(
        @Path("userUID") userUID: String?,
        @Query("auth") key: String = Util.POST_KEY
    ): Observable<JsonObject>

}