package com.example.mvulatrustmobileapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, "Sign.db", null, 1) {
    override fun onCreate(MyDatabase: SQLiteDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)")
    }

    override fun onUpgrade(MyDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        MyDatabase.execSQL("drop Table if exists allusers")
    }

    fun insertData(email: String?, password: String?): Boolean {
        val MyDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("email", email)
        contentValues.put("password", password)
        val result = MyDatabase.insert("allusers", null, contentValues)
        return result != -1L
    }

    fun checkEmail(email: String): Boolean {
        val MyDatabase = this.writableDatabase
        val cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", arrayOf(email))
        return cursor.count > 0
    }

    fun checkEmailandPassword(email: String, password: String): Boolean {
        val MyDatabase = this.writableDatabase
        val cursor = MyDatabase.rawQuery(
            "Select * from allusers where email = ? and password = ?",
            arrayOf(email, password)
        )
        return cursor.count > 0
    }

    companion object {
        const val databaseName = "Sign.db"
    }
}