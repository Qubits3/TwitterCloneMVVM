package com.example.twitterclonemvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.twitterclonemvvm.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.twitter_clone)
    }
}