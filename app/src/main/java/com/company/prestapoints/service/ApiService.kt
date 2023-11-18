package com.company.prestapoints.service

import com.company.prestapoints.model.Categories
import com.company.prestapoints.model.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("endpoint")
    fun getData(@Header("Authorization") authToken: String): Call<Category>

    @GET("getDataWithoutToken")
    fun getDataWithoutToken(): Call<Category>

    @GET("categories")
    fun getCategories(): Call<List<Category>>
}