package com.feature.session.data.network

object BeginSignInRequest {
    signInRequest = BeginSignInRequest.builder()
    .setGoogleIdTokenRequestOptions(
    BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
    .setSupported(true)
    // Your server's client ID, not your Android client ID.
    .setServerClientId(getString(R.string.your_web_client_id))
    // Only show accounts previously used to sign in.
    .setFilterByAuthorizedAccounts(true)
    .build())
    .build()
}