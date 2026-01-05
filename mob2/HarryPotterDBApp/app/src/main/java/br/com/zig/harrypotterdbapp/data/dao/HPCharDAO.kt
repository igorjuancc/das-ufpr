package br.com.zig.harrypotterdbapp.data.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import br.com.zig.harrypotterdbapp.data.db.DBHelper
import br.com.zig.harrypotterdbapp.model.HPChar

class HPCharDAO (private val context: Context) {
    private val dbHelper = DBHelper(context)

    fun addHPChar(hpChar: HPChar): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", hpChar.name)
            put("house", hpChar.house)
            put("ancestry", hpChar.ancestry)
        }
        val id = db.insert(DBHelper.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun updateChar(hpChar: HPChar): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", hpChar.name)
            put("house", hpChar.house)
            put("ancestry", hpChar.ancestry)
        }
        val rowsAffected = db.update(DBHelper.TABLE_NAME, values, "id=?", arrayOf(hpChar.id.toString()))
        db.close()
        return rowsAffected
    }

    fun deleteChar(id: Int): Int {
        val db = dbHelper.writableDatabase
        val rowsDeleted = db.delete(DBHelper.TABLE_NAME, "id=?", arrayOf(id.toString()))
        db.close()
        return rowsDeleted
    }

    fun getAllChars(): List<HPChar> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null)
        // mutableListOf ---> lista que cresce conforme a necessidade
        val charList = mutableListOf<HPChar>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val house = cursor.getString(cursor.getColumnIndexOrThrow("house"))
            val ancestry = cursor.getString(cursor.getColumnIndexOrThrow("ancestry"))
            charList.add(HPChar(id, name, house, ancestry))
        }
        cursor.close()
        db.close()
        return charList
    }

    fun getCharById(id: Int): HPChar? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(DBHelper.TABLE_NAME, null,
            "id=?", arrayOf(id.toString()), null, null, null)

        var hpChar: HPChar? = null
        if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val house = cursor.getString(cursor.getColumnIndexOrThrow("house"))
            val ancestry = cursor.getString(cursor.getColumnIndexOrThrow("ancestry"))
            hpChar = HPChar(id, name, house, ancestry)
        }
        cursor.close()
        db.close()
        return hpChar
    }


}