package com.company.prestapoints

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.company.prestapoints.config.RetrofitConfig
import com.company.prestapoints.model.Categories
import com.company.prestapoints.model.Category
import com.company.prestapoints.service.ApiService
import com.company.prestapoints.ui.theme.PrestapointsTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private val apiService: ApiService = RetrofitConfig.createApiService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrestapointsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    // Appeler l'API et afficher le résultat dans la console de log
                    fetchDataAndLog(apiService)
                }
            }
        }
    }
}
@Composable
fun fetchDataAndLog(apiService: ApiService) {

    // Appel de l'API
    val call = apiService.getCategories()
    Log.d("API Call", "*******************  appel de l'api")
    call.enqueue(object : Callback<List<Category>> {
        override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
            if (response.isSuccessful) {
                val categories = response.body()
                if (categories != null) {
                    // Utilisation de Log pour afficher dans la console de log
                    for (category in categories) {
                        Log.d("API Response", "Category: $category")
                    }
                }
            } else {
                Log.e("API Response", "Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<List<Category>>, t: Throwable) {
            Log.e("API Response", "Error: ${t.message}")
        }
    })
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrestapointsTheme {
        Greeting("Android")
    }
}