package com.company.prestapoints.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.company.prestapoints.R

class FooterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.footer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Vous pouvez accéder aux éléments de votre pied de page ici
        val footerTitle = view.findViewById<TextView>(R.id.footerTitle)
        val footerDescription = view.findViewById<TextView>(R.id.footerDescription)

        // Faites ce que vous devez faire avec ces éléments, si nécessaire
    }
}
