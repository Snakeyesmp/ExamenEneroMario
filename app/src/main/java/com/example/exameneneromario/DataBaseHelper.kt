package com.example.exameneneromario

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.content.contentValuesOf
import java.sql.SQLException
import kotlin.math.log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "TareaDatabase6"
        private const val DATABASE_VERSION = 1
        private const val TABLE_TAREAS = "tareas"
        private const val KEY_ID = "id"
        private const val KEY_NOMBRE = "nombre"
        private const val KEY_FECHA = "fechaVencimiento"
        private const val KEY_COMPLETADA = "completada"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createLocationsTable = ("CREATE TABLE " + TABLE_TAREAS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOMBRE + " VARCHAR,"
                + KEY_FECHA + " INTEGER," + KEY_COMPLETADA + " INTEGER" + ")")
        db?.execSQL(createLocationsTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TAREAS")
        onCreate(db)
    }

    fun addTarea(titulo: String, fecha: Int, completada: Int): Long {
        val db = this.writableDatabase
        val contentValues = contentValuesOf()
        contentValues.put(KEY_NOMBRE, titulo)
        contentValues.put(KEY_FECHA, fecha)
        contentValues.put(KEY_COMPLETADA, completada)
        val success = db.insert(TABLE_TAREAS, null, contentValues)
        db.close()
        Log.e("Dato insertado:", "Tarea ${titulo} fecha ${fecha} completada ${completada}")
        return success
    }

    /*
    fun addTarea2(tarea : Tarea): Long {
        try{
            val db = this.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(KEY_NOMBRE,tarea.nombre)
            contentValues.put(KEY_FECHA,tarea.fecha)
            contentValues.put(KEY_COMPLETADA,tarea.completada)

            val success = db.insert(TABLE_TAREAS,null,contentValues)
            db.close()
            return success
        } catch (e: Exception){
            Log.e("ERROR GORDO","ERROR AL AGREGAR DISCO",e)
            return -1
        }
    }
    */


    fun getAllTareas(): ArrayList<Tarea> {
        val tareasList = ArrayList<Tarea>()
        val selectQuery = "SELECT * FROM $TABLE_TAREAS"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var nombre: String
        var fecha: Int
        var completada: Int

        if (cursor.moveToFirst()) {
            do {
                val idIndex = cursor.getColumnIndex(KEY_ID)
                val nombreIndex = cursor.getColumnIndex(KEY_NOMBRE)
                val fechaIndex = cursor.getColumnIndex(KEY_FECHA)
                val completadaIndex = cursor.getColumnIndex(KEY_COMPLETADA)

                if (idIndex != -1 && nombreIndex != -1 && fechaIndex != -1 && completadaIndex != -1) {
                    id = cursor.getInt(idIndex)
                    nombre = cursor.getString(nombreIndex)
                    fecha = cursor.getInt(fechaIndex)
                    completada = cursor.getInt(completadaIndex)


                    val tarea =
                        Tarea(id = id, nombre = nombre, fecha = fecha, completada = completada)
                    tareasList.add(tarea)

                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tareasList
    }


    fun getTareasCompletadas(): ArrayList<Tarea> {
        val tareasList = ArrayList<Tarea>()
        val selectQuery = "SELECT * FROM $TABLE_TAREAS WHERE completada = 1"
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var nombre: String
        var fecha: Int
        var completada: Int

        if (cursor.moveToFirst()) {
            do {
                val idIndex = cursor.getColumnIndex(KEY_ID)
                val nombreIndex = cursor.getColumnIndex(KEY_NOMBRE)
                val fechaIndex = cursor.getColumnIndex(KEY_FECHA)
                val completadaIndex = cursor.getColumnIndex(KEY_COMPLETADA)

                if (idIndex != -1 && nombreIndex != -1 && fechaIndex != -1 && completadaIndex != -1) {
                    id = cursor.getInt(idIndex)
                    nombre = cursor.getString(nombreIndex)
                    fecha = cursor.getInt(fechaIndex)
                    completada = cursor.getInt(completadaIndex)


                    val tarea =
                        Tarea(id = id, nombre = nombre, fecha = fecha, completada = completada)
                    tareasList.add(tarea)

                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tareasList
    }


    fun getUnaTarea(id: Int): Tarea {
        val tareasList = ArrayList<Tarea>()
        val selectQuery = "SELECT * FROM $TABLE_TAREAS WHERE id = ${id} "
        val db = this.readableDatabase
        var cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return Tarea(0, "", 0, 0)
        }

        var id: Int
        var nombre: String
        var fecha: Int
        var completada: Int

        if (cursor.moveToFirst()) {
            do {
                val idIndex = cursor.getColumnIndex(KEY_ID)
                val nombreIndex = cursor.getColumnIndex(KEY_NOMBRE)
                val fechaIndex = cursor.getColumnIndex(KEY_FECHA)
                val completadaIndex = cursor.getColumnIndex(KEY_COMPLETADA)

                if (idIndex != -1 && nombreIndex != -1 && fechaIndex != -1 && completadaIndex != -1) {
                    id = cursor.getInt(idIndex)
                    nombre = cursor.getString(nombreIndex)
                    fecha = cursor.getInt(fechaIndex)
                    completada = cursor.getInt(completadaIndex)


                    val tarea =
                        Tarea(id = id, nombre = nombre, fecha = fecha, completada = completada)
                    return tarea
                }
            } while (cursor.moveToNext())
        }
        cursor.close()
        return Tarea(0, "", 0, 0)
    }


}