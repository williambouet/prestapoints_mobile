package com.company.prestapoints.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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

        // Récupérer la Toolbar depuis le layout
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        // Configurer la Toolbar comme la barre d'action
        setSupportActionBar(toolbar)

        // Désactiver l'affichage du titre de l'application
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Activer la flèche de retour dans la barre d'action
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Personnaliser l'icône de la flèche de retour (flèche blanche)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)


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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Lorsque le bouton de retour est cliqué, revenez à MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
