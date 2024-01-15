package com.example.exameneneromario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class Fragment_detalles_tarea : Fragment(), Comunicador {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalles_tarea, container, false)

        databaseHelper = DatabaseHelper(requireContext())
    }

    override fun enviarDatos(id: Int) {
        databaseHelper.getUnaTarea(id)
        
    }


}