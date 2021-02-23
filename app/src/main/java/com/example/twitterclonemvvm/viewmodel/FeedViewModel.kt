package com.example.twitterclonemvvm.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.twitterclonemvvm.model.Following
import com.example.twitterclonemvvm.model.Tweet
import com.example.twitterclonemvvm.services.TweetAPIService
import com.example.twitterclonemvvm.services.UserAPIService
import com.example.twitterclonemvvm.util.print
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    private val tweetAPIService = TweetAPIService()
    private val userAPIService = UserAPIService()

    val tweets = MutableLiveData<List<Tweet>>()
    private val userFollowings = MutableLiveData<List<Following>>()

    fun getUserFollowings(userUID: String) {
        val userFollowings = ArrayList<Following>()
        disposable.add(
            userAPIService.getUserFollowings(userUID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<JsonObject>() {
                    override fun onNext(t: JsonObject) {
                        t.keySet().forEach {
                            it?.let {
                                userFollowings.add(Following(it))
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.localizedMessage.print("getUserFollowings:")
                        e.printStackTrace()
                    }

                    override fun onComplete() {
                        updateFollowingList(userFollowings)
                        getTweets()
                    }
                })
        )
    }

    private fun getTweets() {
        val tweets = ArrayList<Tweet>()

        userFollowings.value?.forEach {
            disposable.add(
                tweetAPIService.getTweets(it.followingAccount)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<JsonObject>() {
                        override fun onNext(t: JsonObject) {
                            t.entrySet().forEach { singleTweet ->
                                val tweetJsonObject = singleTweet.value.asJsonObject
                                tweets.add(
                                    Tweet(
                                        tweetJsonObject.get("id").asString,
                                        tweetJsonObject.get("text").asString,
                                        tweetJsonObject.get("userUID").asString,
                                        tweetJsonObject.get("timestamp").asString
                                    )
                                )
                            }
                        }

                        override fun onComplete() {
                            updateTweetList(tweets)
                            "getTweets()".print()
                        }

                        override fun onError(e: Throwable) {
                            e.localizedMessage.print("getTweets:")
                            e.printStackTrace()
                        }
                    })
            )
        }
    }

    private fun updateTweetList(newTweets: List<Tweet>) {
        tweets.value = newTweets
    }

    private fun updateFollowingList(newFollowingList: List<Following>) {
        userFollowings.value = newFollowingList
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}