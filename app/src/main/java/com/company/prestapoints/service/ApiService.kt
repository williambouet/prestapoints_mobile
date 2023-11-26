package com.company.prestapoints.service

import com.company.prestapoints.model.ApiResponse
import com.company.prestapoints.model.Categories
import com.company.prestapoints.model.Category
import com.company.prestapoints.model.UserSignIn
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("endpoint")
    fun getData(@Header("Authorization") authToken: String): Call<Category>

    @GET("getDataWithoutToken")
    fun getDataWithoutToken(): Call<Category>

    @GET("categories")
    fun getCategories(): Call<List<Category>>

    @POST("public/sign-in")
    fun signUp(@Body request: UserSignIn): Call<ApiResponse>
}