package com.company.prestapoints.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.company.prestapoints.config.RetrofitConfig
import com.company.prestapoints.service.ApiService
import com.company.prestapoints.ui.theme.PrestapointsTheme
import androidx.compose.material3.Button


class MainActivity : ComponentActivity() {
    private val apiService: ApiService = RetrofitConfig.createApiService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrestapointsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        // Bouton pour démarrer LoginActivity
                        Button(
                            onClick = {
                                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                                startActivity(intent)
                            }
                        ) {
                            Text("Se connecter")
                        }
                    }
                }
            }
        }
    }
}
