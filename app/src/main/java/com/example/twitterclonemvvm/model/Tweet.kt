package com.example.twitterclonemvvm.model

import androidx.room.Entity

@Entity
data class Tweet(
    var id: String?,
    var text: String?,
    var userUID: String?,
    var timestamp: String?
)