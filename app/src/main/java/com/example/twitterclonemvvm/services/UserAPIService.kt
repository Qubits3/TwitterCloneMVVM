package com.example.twitterclonemvvm.services

import com.example.twitterclonemvvm.model.UserInfo
import com.example.twitterclonemvvm.util.Util
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UserAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(Util.POST_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(UserAPI::class.java)

    fun getUserInfo(userUID: String): Single<UserInfo> {
        return api.getUserInfo(userUID)
    }

    fun setUserInfo(userInfo: UserInfo, userUID: String): Single<UserInfo> {
        return api.setUserInfo(userInfo, userUID)
    }

    fun getUserFollowings(userUID: String): Observable<JsonObject> {
        return api.getUserFollowings(userUID)
    }

}