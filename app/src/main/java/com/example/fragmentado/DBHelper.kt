package com.example.fragmentado

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "pokemon.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "PokemonsGuardados"
        const val COLUMN_ID = "id"
        const val COLUMN_NOME = "nome"
        const val COLUMN_TYPE1 = "type1"
        const val COLUMN_TYPE2 = "type2"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val crateTable: String = ("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOME + " TEXT, "
                + COLUMN_TYPE1 + " TEXT, "
                + COLUMN_TYPE2 + " TEXT)")
        db.execSQL(crateTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(
        nome: String,
        type1: String,
        type2: String
    ): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NOME, nome)
        contentValues.put(COLUMN_TYPE1, type1)
        contentValues.put(COLUMN_TYPE2, type2)
        val result: Long = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    fun getAllData(): List<DBPokemon> {
        val list = mutableListOf<DBPokemon>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME))
                val type1 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE1))
                val type2 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE2))

                list.add(
                    DBPokemon(
                        id,
                        nome,
                        type1,
                        type2
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}