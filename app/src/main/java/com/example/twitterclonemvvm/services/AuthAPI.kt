package com.example.twitterclonemvvm.services

import com.example.twitterclonemvvm.model.Auth
import com.example.twitterclonemvvm.util.Util
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthAPI {

    @POST("signupNewUser")
    fun signUp(@Body auth: Auth, @Query("key") key: String = Util.AUTH_KEY): Single<JsonObject>

    @POST("verifyPassword")
    fun signIn(@Body auth: Auth, @Query("key") key: String = Util.AUTH_KEY): Single<JsonObject>
}