package com.example.twitterclonemvvm.services

import com.example.twitterclonemvvm.model.UserInfo
import com.example.twitterclonemvvm.util.Util
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserAPI {

    @GET("users/{userUID}/info.json")
    fun getUserInfo(
        @Path("userUID") userUID: String?,
        @Query("auth") key: String = Util.POST_KEY
    ): Single<UserInfo>

    @GET("users/{userUID}/following.json")
    fun getUserFollowings(
        @Path("userUID") userUID: String?,
        @Query("auth") key: String = Util.POST_KEY
    ): Observable<JsonObject>

}