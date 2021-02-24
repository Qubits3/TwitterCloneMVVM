package com.example.twitterclonemvvm.viewmodel

import android.app.Application
import android.view.View
import androidx.navigation.Navigation
import com.example.twitterclonemvvm.model.Auth
import com.example.twitterclonemvvm.model.UserInfo
import com.example.twitterclonemvvm.services.AuthAPIService
import com.example.twitterclonemvvm.services.UserAPIService
import com.example.twitterclonemvvm.util.print
import com.example.twitterclonemvvm.view.SignUpFragmentDirections
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SignUpViewModel(application: Application) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    private val authAPIService = AuthAPIService()
    private val userAPIService = UserAPIService()

    fun signUp(view: View, auth: Auth, userName: String, profileImageUrl: String, description: String) {
        disposable.add(
            authAPIService.signUp(auth)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<JsonObject>() {
                    override fun onSuccess(t: JsonObject) {
                        setUserInfo(view, t.get("localId").asString, userName, profileImageUrl, t.get("email").asString, description)
                    }

                    override fun onError(e: Throwable) {
                        e.localizedMessage.print()
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun setUserInfo(view: View, userUID: String, userName: String, profileImageUrl: String, email: String, description: String) {
        disposable.add(
            userAPIService.setUserInfo(UserInfo(userUID, userName, profileImageUrl, email, description), userUID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserInfo>() {
                    override fun onSuccess(t: UserInfo) {
                        Navigation.findNavController(view).navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment(userUID))
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