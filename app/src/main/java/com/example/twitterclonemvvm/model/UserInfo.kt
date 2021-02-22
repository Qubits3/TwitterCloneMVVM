package com.example.twitterclonemvvm.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class UserInfo(
    var userUID: String?,
    var username: String?,
    @SerializedName("profile_image_url")
    var profileImageUrl: String?,
    var email: String?,
    var description: String
)
