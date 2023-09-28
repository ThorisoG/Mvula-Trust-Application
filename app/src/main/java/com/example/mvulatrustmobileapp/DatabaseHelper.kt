package com.example.mvulatrustmobileapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, "Sign.db", null, 1) {
    override fun onCreate(MyDatabase: SQLiteDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)")

        //thori section of the code//
        //Priority HIGH//
        //The tables are here but the main issue is that it doesn't create the tables for some odd reason please check it out//
        MyDatabase.execSQL("create Table Administration(adminEmail TEXT primary key,adminPassword TEXT)")
        MyDatabase.execSQL("Insert into Administration(adminEmail,adminPassword) VALUES('Admin101@mvula.com','admin101')")

        MyDatabase.execSQL("create Table Volunteer(Vname TEXT primary key,Vidnum TEXT,Phonenum TEXT,Email TEXT,HomeAddress TEXT,Program TEXT,Qualification TEXT,VolunteerStatus TEXT)")
        MyDatabase.execSQL("INSERT into Volunteer (Vname, Vidnum, Phonenum, Email, HomeAddress, Program, Qualification, VolunteerStatus) VALUES ('Thoriso', '00023445523', '0814893034', 'Jack@gmail.com', '10 jojo street mashville', 'Water Resource Management', 'Degree', 'Pending')")
        MyDatabase.execSQL("INSERT into Volunteer (Vname, Vidnum, Phonenum, Email, HomeAddress, Program, Qualification, VolunteerStatus) VALUES ('Dex', '00023445523', '0814893034', 'Jack@gmail.com', '10 jojo street mashville', 'Water Resource Management', 'Degree', 'Pending')")
        MyDatabase.execSQL("INSERT into Volunteer (Vname, Vidnum, Phonenum, Email, HomeAddress, Program, Qualification, VolunteerStatus) VALUES ('Maggy', '00023445523', '0814893034', 'Jack@gmail.com', '10 jojo street mashville', 'Water Resource Management', 'Degree', 'Pending')")
        MyDatabase.execSQL("INSERT into Volunteer (Vname, Vidnum, Phonenum, Email, HomeAddress, Program, Qualification, VolunteerStatus) VALUES ('Josh', '00023445523', '0814893034', 'Jack@gmail.com', '10 jojo street mashville', 'Water Resource Management', 'Degree', 'Pending')")

        MyDatabase.execSQL("create table Donations(Donname TEXT,Amount Double)")
        MyDatabase.execSQL("Insert into Donations(Donname,Amount) Values('Jack',1200)")
        MyDatabase.execSQL("Insert into Donations(Donname,Amount) Values('Maggy',200)")
        MyDatabase.execSQL("Insert into Donations(Donname,Amount) Values('Zoey',1320)")
        MyDatabase.execSQL("Insert into Donations(Donname,Amount) Values('Dory',700)")


    }

    override fun onUpgrade(MyDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        MyDatabase.execSQL("drop Table if exists allusers")
        MyDatabase.execSQL("drop Table if exists Administration")
        MyDatabase.execSQL("drop table if exists Volunteer")
        MyDatabase.execSQL("drop Table if exists Donations")
        onCreate(MyDatabase)
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
    //This Function is for viewing all volunteer applications and choosing to either accept or decline
    fun getPendingVolunteers(): Cursor {
        val MyDatabase = this.writableDatabase
        return MyDatabase.rawQuery("SELECT * FROM Volunteer WHERE VolunteerStatus='Pending'", null)
    }
    //This Function is for Updating all volunteer applications for accept or reject//
    fun updateVolunteerStatus(Vname: String, status: String): Boolean {
        val MyDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("VolunteerStatus", status)
        val result = MyDatabase.update("Volunteer", contentValues, "Vname = ?", arrayOf(Vname))
        return result > 0
    }
    //This Function its to display the sum of the Total Donatiosn
    fun getTotalDonationAmount(): Double {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT SUM(Amount) FROM Donations", null)

        var totalAmount = 0.0

        if (cursor.moveToFirst()) {
            totalAmount = cursor.getDouble(0)
        }

        cursor.close()
        db.close()

        return totalAmount
    }
    //This Function its to display the donation and donation amount to the List//
    fun getAllDonations(): List<String> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from Donations;", null)

        val donationsList = mutableListOf<String>()

        while (cursor.moveToNext()) {
            val donatorName = cursor.getString(cursor.getColumnIndex("Donname"))
            val amount = cursor.getDouble(cursor.getColumnIndex("Amount"))
            donationsList.add("$donatorName: $amount")
        }

        cursor.close()
        db.close()

        return donationsList
    }



    companion object {
        const val databaseName = "Sign.db"
    }
}