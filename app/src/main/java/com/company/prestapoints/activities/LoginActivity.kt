package com.company.prestapoints.activities

import AuthenticationService
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.company.prestapoints.R
import com.company.prestapoints.R.*

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var authService: AuthenticationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialiser l'instance de AuthenticationService
        authService = AuthenticationService(this)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Personnaliser l'icône de la flèche de retour
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)

        // Récupérer les références des éléments du formulaire depuis le layout
        usernameEditText = findViewById(R.id.editTextUsername)
        passwordEditText = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)

        // Définir le gestionnaire de clic pour le bouton de connexion
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Vérifier si les champs ne sont pas vides
            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Appeler la fonction de connexion de AuthenticationService
                authService.login(username, password) { isLoggedIn ->
                    if (isLoggedIn) {
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        showFailureDialog()
                    }
                }
            } else {
                showFillFieldsDialog()
            }
        }

        // Activer la flèche de retour dans la barre d'action
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    fun onSignIn(view: View) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun showFailureDialog() {
        AlertDialog.Builder(this)
            .setTitle("Échec de la connexion")
            .setMessage("Veuillez vérifier vos informations d'identification.")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showFillFieldsDialog() {
        AlertDialog.Builder(this)
            .setTitle("Champs vides")
            .setMessage("Veuillez remplir tous les champs.")
            .setPositiveButton("OK", null)
            .show()
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
