package com.example.exameneneromario

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment


class Fragment_ListaTareas : Fragment() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var listViewTareas: ListView
    private lateinit var tareasArray: Array<String>
    private lateinit var adapter : ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_tareas, container, false)
    }


    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewTareas = view.findViewById(R.id.listview_tareas)
        databaseHelper = DatabaseHelper(requireContext())

        val tareas = databaseHelper.getAllTareas()



        var textoCompletada : String

        tareasArray = Array(tareas.size) { i ->


            if (tareas[i].completada == 0){
                textoCompletada = "No"
            }else{
                textoCompletada = "Si"
            }

            "ID: ${tareas[i].id}, Nombre: ${tareas[i].nombre} , Completada: ${textoCompletada}"}

        adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1, tareasArray)
        listViewTareas.adapter = adapter


        listViewTareas.setOnItemClickListener( { arg0, arg1, position, arg3 ->
            Log.i("Click", "click en el elemento $position de mi ListView")

            (activity as? Comunicador)?.enviarDatos(position)

        })

    }



}