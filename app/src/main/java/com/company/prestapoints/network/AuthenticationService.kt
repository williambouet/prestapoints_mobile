package com.company.prestapoints.network

import TokenResponse
import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthenticationService(private val context: Context) {

    private val authUrl = "https://staging.prestapoints.lille-1.wilders.dev/api/"
    private val authService: AuthService

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(authUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authService = retrofit.create(AuthService::class.java)
    }

    fun login(username: String, password: String, callback: (Boolean) -> Unit) {
        // Convertir les informations d'identification en Base64
        val credentials = "$username:$password"
        val base64Credentials = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
        // Effectuer la demande d'authentification avec Retrofit
        val call = authService.authenticate(username, password, "Basic $base64Credentials")
        call.enqueue(object : Callback<TokenResponse> {
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                try {
                    if (response.isSuccessful) {
                        val token = response.body()?.token
                        saveToken(token)
                        Log.d("com.company.prestapoints.network.AuthenticationService", "Token: $token")
                        callback(true)
                    } else {
                        Log.d("com.company.prestapoints.network.AuthenticationService", "Response Code: ${response.code()}")
                        Log.d("com.company.prestapoints.network.AuthenticationService", "Response Message: ${response.message()}")
                        Log.d("com.company.prestapoints.network.AuthenticationService",">>>>>>>>>>>>>>>>>>>>>> echec !!")
                        callback(false)
                    }
                }catch (e: Exception) {
                    Log.e("com.company.prestapoints.network.AuthenticationService", "Message >>>>>>>>>>>>> ", e)
                    callback(false)
                }

            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                callback(false)
            }
        })
    }




    fun getToken(): String {
        return sharedPreferences.getString("token", "") ?: ""
    }

    fun logout() {
        // Effacer le token
        saveToken("")
    }

    fun isLoggedIn(): Boolean {
        return getToken().isNotBlank()
    }

    private fun saveToken(token: String?) {
        sharedPreferences.edit().putString("token", token).apply()
    }
}
