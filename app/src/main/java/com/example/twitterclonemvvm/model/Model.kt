package com.example.twitterclonemvvm.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Model(

    var user: UserInfo,
    var following: Following
)
