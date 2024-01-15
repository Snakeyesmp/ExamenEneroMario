package com.example.exameneneromario

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var buttonAñadir : Button
    private lateinit var buttonCompletadas : Button
    private lateinit var intentActivityFormulario : Intent
    private lateinit var intentActivityCompletadas : Intent
    private var currentSize : Float = 12f

    private lateinit var databaseHelper: DatabaseHelper


    // Definición de constantes dentro de un objeto companion. Son accesibles desde la clase sin necesidad de una instancia
    companion object {
        const val PREFS_NAME = "MyPrefsFile" // Nombre del archivo de preferencias compartidas
        const val FONT_SIZE_KEY = "fontSize" // Clave para almacenar el color de fondo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Agregar ambos fragmentos a sus respectivos contenedores solo si savedInstanceState es nulo
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentLista, Fragment_ListaTareas())
                .replace(R.id.fragmentDetalles, Fragment_detalles_tarea())
                .commit()
        }

        databaseHelper = DatabaseHelper(this) // Para la BBDD


        // Acceso a las preferencias compartidas
        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Recupera el color actual guardado en las preferencias, o azul como valor predeterminado
        currentSize = sharedPref.getFloat(FONT_SIZE_KEY, 12f)

        // Se crean los intent
        intentActivityFormulario = Intent(this,ActivityFormulario::class.java)
        intentActivityCompletadas = Intent(this,ActivityTareasCompletadas::class.java)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        buttonAñadir = findViewById(R.id.button_añadir)
        buttonAñadir.setOnClickListener{
            irAFormulario()
        }

        // buttonAñadir.textSize = 12f

        buttonCompletadas = findViewById(R.id.button_completadas)
        buttonCompletadas.setOnClickListener{
            irATareasCompletadas()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        return when (item.itemId){
            R.id.menuitem_completar ->{

                true
            }
            R.id.menuitem_modificar ->{

                true
            }
            R.id.menuitem_eliminar ->{

                true
            }
            R.id.menuitem_enviar ->{
                enviarCorreo("Correo@correo.es","Asunto","Mensaje")
                true
            }
            R.id.cambiarFuente -> {
                var newTamaño = if (currentSize == 12f) 32f else 12f
                    buttonAñadir.textSize = newTamaño
                currentSize = newTamaño
                true
            }

            else -> {

                true
            }
        }

    }

    fun irAFormulario(){
        startActivity(intentActivityFormulario)
    }

    fun irATareasCompletadas(){
         startActivity(intentActivityCompletadas)
    }


    private fun enviarCorreo(recipient: String, subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)


        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }


}