package com.company.prestapoints.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.prestapoints.R
import com.company.prestapoints.config.RetrofitConfig
import com.company.prestapoints.fragments.PrestationAdapter
import com.company.prestapoints.model.Prestation
import com.company.prestapoints.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

// PrestationFragment.kt
class PrestationFragment : Fragment() {

    private val apiService: ApiService = RetrofitConfig.createApiService()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PrestationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_prestation_card, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        // Adapter initialisé avec une liste vide
        adapter = PrestationAdapter(emptyList())
        recyclerView.adapter = adapter

        // Appeler la fonction pour obtenir les prestations
        fetchPrestationsAndLog(apiService)

        return view
    }

    private fun fetchPrestationsAndLog(apiService: ApiService) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val prestations = withContext(Dispatchers.IO) {
                    apiService.getPrestations().toList()
                }

                adapter.updateData(prestations)

            } catch (e: HttpException) {
                Log.e("Prestation", "HTTP Error: ${e.code()}")
            } catch (e: Exception) {
                Log.e("Prestation", "Error: ${e.message}")
            }
        }
    }
}
