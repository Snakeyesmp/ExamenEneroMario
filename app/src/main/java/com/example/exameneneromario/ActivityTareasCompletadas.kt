package com.example.exameneneromario

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ActivityTareasCompletadas : AppCompatActivity() {

    private lateinit var intentActivityFormulario: Intent
    private lateinit var buttonVolver : Button

    private lateinit var listviewCompletadas : ListView

    private lateinit var databaseHelper : DatabaseHelper

    private lateinit var tareasArray: Array<String>
    private lateinit var adapter : ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tareas_completadas)

        databaseHelper = DatabaseHelper(this)

        mostrarTareasCompletadas()


        intentActivityFormulario = Intent(this,ActivityFormulario::class.java)

        buttonVolver = findViewById(R.id.button_volver_activity3)
        buttonVolver.setOnClickListener{
            irAActivityPrincipal()
        }



    }

    fun irAFormulario(){
        startActivity(intentActivityFormulario)
    }

    fun irAActivityPrincipal(){
        finish()
    }






    fun mostrarTareasCompletadas(){

        listviewCompletadas = findViewById(R.id.listview_completadas)
        databaseHelper = DatabaseHelper(this)

        val tareas = databaseHelper.getTareasCompletadas()

        var textoCompletada : String

        tareasArray = Array(tareas.size) { i ->


            if (tareas[i].completada == 0){
                textoCompletada = "No"
            }else{
                textoCompletada = "Si"
            }

            "ID: ${tareas[i].id}, Nombre: ${tareas[i].nombre} , Completada: ${textoCompletada}"}

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, tareasArray)
        listviewCompletadas.adapter = adapter

    }








}