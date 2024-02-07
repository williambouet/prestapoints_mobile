package com.company.prestapoints.ui.viewModels

import androidx.lifecycle.ViewModel
import com.company.prestapoints.model.UserSignIn
import com.company.prestapoints.network.ApiService
import com.company.prestapoints.util.RetrofitConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class SignInViewModel : ViewModel() {

    private val apiService: ApiService = RetrofitConfig.createApiService()

    suspend fun signIn(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        phone: String
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val signUpRequest = UserSignIn(firstName, lastName, email, password, phone)
                val response = apiService.signUp(signUpRequest).execute()
                response.isSuccessful && response.body()?.responseValid == true
            } catch (e: HttpException) {
                false
            }
        }
    }
}
