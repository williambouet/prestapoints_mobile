package com.company.prestapoints.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.company.prestapoints.R
import com.company.prestapoints.config.RetrofitConfig
import com.company.prestapoints.model.UserSignIn
import com.company.prestapoints.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInActivity : AppCompatActivity() {

    private val apiService: ApiService = RetrofitConfig.createApiService()
    private lateinit var editFirstName: EditText
    private lateinit var editLastName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var buttonSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Initialise les éléments de l'interface utilisateur en utilisant findViewById
        editFirstName = findViewById(R.id.editFirstName)
        editLastName = findViewById(R.id.editLastName)
        editEmail = findViewById(R.id.editEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPhone = findViewById(R.id.editTextPhone)
        buttonSignIn = findViewById(R.id.buttonSignIn)
    }

    fun onSubmitFormSignIn(view: View) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val signUpRequest = UserSignIn(
                    editFirstName.text.toString(),
                    editLastName.text.toString(),
                    editEmail.text.toString(),
                    editTextPassword.text.toString(),
                    editTextPhone.text.toString()
                )

                Log.d("SignInService", signUpRequest.firstname)
                Log.d("SignInService", signUpRequest.lastname)

                val response = withContext(Dispatchers.IO) {
                    apiService.signUp(signUpRequest).execute()
                }

                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse?.responseValid == true) {
                        Log.d("SignInService", ">>>>>>>>>>>>>>>>>>>>>>> inscrit!!")
                    } else {
                        Log.d("SignInService", ">>>>>>>>>>>>>>>>>>>>>>> echec !!" + apiResponse?.message + signUpRequest.email)
                    }
                } else {
                    Log.d("SignInService", ">>>>>>>>>>>>>>>>>>>>>>> erreur Réseau !!")
                }
            } catch (e: Exception) {
                Log.e("SignInService", "Exception: ${e.message}")
            }
        }
    }
}
