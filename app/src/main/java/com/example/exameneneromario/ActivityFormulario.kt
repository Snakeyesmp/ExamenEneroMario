package com.example.exameneneromario

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.sql.Date

class ActivityFormulario : AppCompatActivity(){

    private lateinit var editTextNombre : EditText
    private lateinit var editTextFecha : EditText

    private lateinit var buttonVolver2 : Button
    private lateinit var buttonLimpiar: Button
    private lateinit var buttonGuardar: Button

    private lateinit var checkBoxCompletada: CheckBox

    private lateinit var databaseHelper: DatabaseHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        databaseHelper = DatabaseHelper(this)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextFecha = findViewById(R.id.editTextDate)

        buttonVolver2 = findViewById(R.id.button_volver)
        buttonLimpiar = findViewById(R.id.button_limpiar)
        buttonGuardar = findViewById(R.id.button_guardar)

        checkBoxCompletada = findViewById(R.id.cb_completada)

        buttonVolver2.setOnClickListener{
            finish()
        }

        buttonLimpiar.setOnClickListener{
                vaciarCampos()
        }

        buttonGuardar.setOnClickListener{
            guardarTareaBBDD()
            vaciarCampos()
        }

    }


    fun vaciarCampos() {
        editTextNombre.setText("")
        editTextFecha.setText("")
    }

    fun guardarTareaBBDD(){
        val nombreTarea = editTextNombre.text
        val completada : Int
        if (checkBoxCompletada.isChecked){
            completada = 1
        }else{
            completada = 0
        }

        databaseHelper.addTarea(nombreTarea.toString() ,1234,completada)
        mostrarToast(nombreTarea.toString())

    }

    fun mostrarToast(nombreTarea : String){
        Toast.makeText(
            this,
            "Tarea ${nombreTarea} insertada correctamente",
            Toast.LENGTH_LONG
        ).show()
    }

}