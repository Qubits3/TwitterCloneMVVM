package com.example.twitterclonemvvm.services

import com.example.twitterclonemvvm.model.Auth
import com.example.twitterclonemvvm.util.Util
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AuthAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(Util.AUTH_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(AuthAPI::class.java)

    fun signUp(auth: Auth): Single<JsonObject> {
        return api.signUp(auth)
    }

    fun signIn(auth: Auth): Single<JsonObject> {
        return api.signIn(auth)
    }

}