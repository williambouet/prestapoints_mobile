package com.company.prestapoints.network

import TokenResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("auth")
    fun authenticate(
        @Field("username") username: String,
        @Field("password") password: String,
        @Header("Authorization") authorization: String = "Basic"
    ): Call<TokenResponse>
}
