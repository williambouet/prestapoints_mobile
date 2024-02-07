package com.company.prestapoints.ui.activities
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.company.prestapoints.R
import com.company.prestapoints.model.UserSignIn
import com.company.prestapoints.ui.viewModels.SignInViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.android.synthetic.main.activity_sign_in.*



class SignInActivity : AppCompatActivity() {

    private lateinit var signInViewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)

        signInViewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
    }

    fun onSubmitFormSignIn(view: View) {
        val firstName = editFirstName.text.toString()
        val lastName = editLastName.text.toString()
        val email = editEmail.text.toString()
        val password = editTextPassword.text.toString()
        val phone = editTextPhone.text.toString()

        GlobalScope.launch {
            val success = signInViewModel.signIn(firstName, lastName, email, password, phone)
            if (success) {
                runOnUiThread {
                    // Affichage du message de confirmation
                    val message = "Inscription réussie ! Bienvenue sur notre application."
                    afficherMessageConfirmation(this@SignInActivity, message)
                    // Redirection vers la page d'accueil
                    redirigerVersPageAccueil()
                }
                Log.d("SignInActivity", "Inscription réussie pour l'utilisateur : $firstName $lastName")
            } else {
                Log.e("SignInActivity", "Échec de l'inscription pour l'utilisateur : $firstName $lastName")
            }
        }
    }

    // Fonction pour afficher un message de confirmation
    private fun afficherMessageConfirmation(context: Context, message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }

    // Fonction pour rediriger l'utilisateur vers la page d'accueil
    private fun redirigerVersPageAccueil() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this, MainActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}