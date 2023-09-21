package com.example.mvulatrustmobileapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, "Sign.db", null, 1) {
    override fun onCreate(MyDatabase: SQLiteDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)")

        //thori section of the code//
        //MyDatabase.execSQL("create Table Administration(adminEmail TEXT primary key,adminPassword TEXT)")
        //MyDatabase.execSQL("Insert into Administration(adminEmail,adminPassword) VALUES('Admin101@mvula.com','admin101')")
    }

    override fun onUpgrade(MyDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        MyDatabase.execSQL("drop Table if exists allusers")
        //MyDatabase.execSQL("drop Table if exists Administration")
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
            //Added only registration status//
            "Select * from allusers where email = ? and password = ?",
            arrayOf(email, password)
        )
        return cursor.count > 0
    }


    //This is for viewing all users but its in beta testing//
    fun getAllUsers(): Cursor {
        val MyDatabase = this.writableDatabase
        return MyDatabase.rawQuery("SELECT * FROM allusers", null)
    }

    //This function its to delete the user from the database beta experimental//
    fun deleteUser(email: String): Boolean {
        val MyDatabase = this.writableDatabase
        val result = MyDatabase.delete("allusers", "email = ?", arrayOf(email))
        return result != -1
    }

    companion object {
        const val databaseName = "Sign.db"
    }
}