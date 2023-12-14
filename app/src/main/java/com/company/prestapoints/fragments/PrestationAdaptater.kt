package com.company.prestapoints.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.prestapoints.R
import com.company.prestapoints.model.Prestation


class PrestationAdapter(private var prestations: List<Prestation>) :

    RecyclerView.Adapter<PrestationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView? = itemView.findViewById(R.id.titlePrestation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prestation_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prestation = prestations[position]
        val prestationTitleLength: Int = prestation.title.length
        if(prestationTitleLength > 20){
            holder.titleTextView?.text = prestation.title.substring(0, 20)+ " ..."
        }else{
            holder.titleTextView?.text = prestation.title
        }

    }

    override fun getItemCount(): Int {
        return prestations.size
    }

    fun updateData(newPrestations: List<Prestation>) {
        prestations = newPrestations
        notifyDataSetChanged()
    }

}
