package com.company.prestapoints.fragments

import CategoryCardFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.prestapoints.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // Ajouter dynamiquement le fragment card
        val cardFragment = CategoryCardFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, cardFragment)
            .commit()

        return rootView
    }
}