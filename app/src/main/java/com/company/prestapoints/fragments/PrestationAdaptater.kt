package com.company.prestapoints.fragments

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.prestapoints.R
import com.company.prestapoints.model.Prestation
import com.bumptech.glide.Glide
import com.company.prestapoints.config.RetrofitConfig
import com.company.prestapoints.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val apiService: ApiService = RetrofitConfig.createApiService()
class PrestationAdapter(private var prestations: List<Prestation>) :

    RecyclerView.Adapter<PrestationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView? = itemView.findViewById(R.id.titlePrestation)
        val imageView: ImageView? = itemView.findViewById(R.id.imageViewPrestation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prestation_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prestation = prestations[position]
        val prestationTitleLength: Int = prestation.title.length
        if (prestationTitleLength > 20) {
            holder.titleTextView?.text = prestation.title.substring(0, 20) + " ..."
        } else {
            holder.titleTextView?.text = prestation.title
        }

        // Chargez l'image uniquement si la liste d'images n'est pas vide
        if (prestation.images.isNotEmpty()) {
            // Utilisez Glide pour charger l'image dans l'ImageView
            loadImageDataAndDisplay(holder, prestation.images[0].id)
        }
    }

    override fun getItemCount(): Int {
        return prestations.size
    }

    fun updateData(newPrestations: List<Prestation>) {
        prestations = newPrestations
        notifyDataSetChanged()
    }

    private fun loadImageDataAndDisplay(holder: ViewHolder, imageId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val imageData = apiService.getImageById(imageId).execute()

                val bitmap = BitmapFactory.decodeStream(imageData.body()?.byteStream())

                withContext(Dispatchers.Main) {
                    holder.imageView?.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                Log.e("PrestationAdapter", "Error loading image data: ${e.message}")
            }
        }
    }



}

