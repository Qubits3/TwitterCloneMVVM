package com.example.twitterclonemvvm.viewmodel

import android.app.Application
import com.example.twitterclonemvvm.model.Tweet
import com.example.twitterclonemvvm.services.TweetAPIService
import com.example.twitterclonemvvm.util.print
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TweetViewModel(application: Application) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    private val tweetAPIService = TweetAPIService()

    fun sendTweet(userUID: String?) {

        val tweet = Tweet("id", "text", "DG46DFHG4F6HGF4H", "4684+846486")

        disposable.add(
            tweetAPIService.sendTweet(tweet, userUID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<JsonObject>() {
                    override fun onSuccess(t: JsonObject) {
                        "TweetViewModel.OnSuccess()".print()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}