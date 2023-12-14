package com.company.prestapoints.fragments

import CategoryCardFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.prestapoints.R
import com.company.prestapoints.ui.PrestationFragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // Afficher le fragment de prestation
        val cardPrestation = PrestationFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerPrestation, cardPrestation)
            .commit()

        // Afficher le fragment de catégorie
        val cardFragment = CategoryCardFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerCategory, cardFragment)
            .commit()

        return rootView
    }
}
