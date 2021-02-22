package com.example.twitterclonemvvm.model

import androidx.room.Entity

@Entity
data class Following(
    var followingAccount: List<String>?
)
