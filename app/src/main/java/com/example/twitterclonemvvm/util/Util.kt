package com.example.twitterclonemvvm.util

object Util {

    const val POST_BASE_URL = "https://twitterclonemvvm-default-rtdb.firebaseio.com/"
    const val AUTH_BASE_URL = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/"
    const val POST_KEY = "M8f8LG9vHXYZTUnIjanzfUQ7Nx7iVIoYgukEOzYD"
    const val AUTH_KEY = "AIzaSyCmo6Y7udL6BpAi8zATRTgqNrYMp2E0uSc"  // WEB API KEY

}

fun Any?.print(prefix: String = ""): Any? {
    println("$prefix $this")
    return this
}