package com.example.twitterclonemvvm.viewmodel

import android.app.Application
import android.view.View
import androidx.navigation.Navigation
import com.example.twitterclonemvvm.model.Auth
import com.example.twitterclonemvvm.services.AuthAPIService
import com.example.twitterclonemvvm.util.print
import com.example.twitterclonemvvm.view.SignInFragmentDirections
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SignInViewModel(application: Application) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    private val authAPIService = AuthAPIService()

    fun signIn(view: View, auth: Auth) {
        disposable.add(
            authAPIService.signIn(auth)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<JsonObject>() {
                    override fun onSuccess(t: JsonObject) {
                        Navigation.findNavController(view).navigate(SignInFragmentDirections.actionSignInFragmentToFeedFragment(t.get("localId").asString))
                    }

                    override fun onError(e: Throwable) {
                        e.localizedMessage.print()
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