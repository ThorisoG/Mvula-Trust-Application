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
        //The tables are here but the main issue is that it doesn't create the tables for some odd reason please check it out//
        MyDatabase.execSQL("create Table Administration(adminEmail TEXT primary key,adminPassword TEXT,AccountStatus TEXT)")
        MyDatabase.execSQL("Insert into Administration(adminEmail, adminPassword, AccountStatus) VALUES('Admin101@mvula.com', 'admin101', 'Activated')")
        MyDatabase.execSQL("Insert into Administration(adminEmail, adminPassword, AccountStatus) VALUES('Admin200@mvula.com', 'admin200', 'Activated')")
        MyDatabase.execSQL("Insert into Administration(adminEmail, adminPassword, AccountStatus) VALUES('Admin102@mvula.com', 'admin102', 'Deactivated')")

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

        MyDatabase.execSQL("Create table Messages(Firstname TEXT primary key, Lastname TEXT, PhoneNum TEXT, Email TEXT, Message TEXT, MessageStatus TEXT)")
        MyDatabase.execSQL("Insert into Messages(Firstname, Lastname, PhoneNum, Email, Message, MessageStatus) Values('Zoe','Smith','0625664216' ,'Zoe.Smith211@gmail.com','Requesting direct admin/hr email address for communication purposes.','Pending')")
        MyDatabase.execSQL("Insert into Messages(Firstname, Lastname, PhoneNum, Email, Message, MessageStatus) Values('Seth','McMillan','0812133765','McMillanSeth05@gmail.com','Requesting company bank account details for a larger sum of donation.','Pending')")
    }

    override fun onUpgrade(MyDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        MyDatabase.execSQL("drop Table if exists allusers")
        MyDatabase.execSQL("drop Table if exists Administration")
        MyDatabase.execSQL("drop table if exists Volunteer")
        MyDatabase.execSQL("drop Table if exists Donations")
        MyDatabase.execSQL("drop Table if exists Messages")
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
    //This Function its to Activate and Deactivate the selected AdminAccount//
    fun getAdmins(): List<List<String>> {
        val db = this.readableDatabase
        val adminsList = mutableListOf<List<String>>()

        val cursor = db.rawQuery("Select * from Administration where AccountStatus='Activated'", null)

        while (cursor.moveToNext()) {
            val email = cursor.getString(cursor.getColumnIndex("adminEmail"))
            //val password = cursor.getString(cursor.getColumnIndex("adminPassword"))
            val accountStatus = cursor.getString(cursor.getColumnIndex("AccountStatus"))
            val adminDetails = listOf(email, accountStatus)
            //val adminDetails = listOf(email, password, accountStatus)
            adminsList.add(adminDetails)
        }

        cursor.close()
        db.close()
        return adminsList
    }
    fun deactivateAdmin(email: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("AccountStatus", "Deactivated")
        }
        val result = db.update("Administration", contentValues, "adminEmail = ?", arrayOf(email))
        db.close()
        return result > 0
    }

    fun getDeactivatedAdmins(): List<List<String>> {
        val db = this.readableDatabase
        val adminsList = mutableListOf<List<String>>()

        val cursor = db.rawQuery("Select * from Administration where AccountStatus='Deactivated'", null)

        while (cursor.moveToNext()) {
            val email = cursor.getString(cursor.getColumnIndex("adminEmail"))
            val password = cursor.getString(cursor.getColumnIndex("adminPassword"))
            val accountStatus = cursor.getString(cursor.getColumnIndex("AccountStatus"))

            val adminDetails = listOf(email, password, accountStatus)
            adminsList.add(adminDetails)
        }

        cursor.close()
        db.close()

        return adminsList
    }

    fun activateAdmin(email: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("AccountStatus", "Activated")
        }
        val result = db.update("Administration", contentValues, "adminEmail = ?", arrayOf(email))
        db.close()
        return result > 0
    }
    //letting the Admin login//
    fun checkAdminLogin(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "Select * from Administration where adminEmail = ? and adminPassword = ? and AccountStatus = 'Activated'",
            arrayOf(email, password)
        )
        return cursor.count > 0
    }
    //Verifying the validity of the Admins Account//
    fun getAdminDeactivationStatus(email: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("Select * from Administration where adminEmail = ? and AccountStatus = 'Deactivated'", arrayOf(email))
        return cursor.count > 0
    }
    //Notification System//
    fun getNewRegistrationsCount(): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM allusers", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        db.close()
        return count
    }

    fun getNewVolunteersCount(): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM Volunteer WHERE VolunteerStatus='Pending'", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        db.close()
        return count
    }

    fun getNewDonationsCount(): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM Donations", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        db.close()
        return count
    }

    //Letting the administrator Update their Password//
    fun updateAdminPassword(email: String, newPassword: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("adminPassword", newPassword)
        }
        val result = db.update("Administration", contentValues, "adminEmail = ?", arrayOf(email))
        db.close()
        return result > 0
    }

    //Letting the user Be able to Apply for volunteer applications//
    fun insertVolunteerData(
        name: String,
        idNum: String,
        phoneNum: String,
        email: String,
        address: String,
        program: String,
        qualification: String,
        volunteerStatus: String
    ): Boolean {
        val MyDatabase = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("Vname", name)
            put("Vidnum", idNum)
            put("Phonenum", phoneNum)
            put("Email", email)
            put("HomeAddress", address)
            put("Program", program)
            put("Qualification", qualification)
            put("VolunteerStatus", volunteerStatus)
        }
        val result = MyDatabase.insert("Volunteer", null, contentValues)
        MyDatabase.close()
        return result != -1L
    }
    // Allowing the user to insert into the database//
    fun insertDonation(donname: String, amount: Double): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("Donname", donname)
            put("Amount", amount)
        }
        val result = db.insert("Donations", null, contentValues)
        db.close()
        return result != -1L
    }
    fun getApprovedVolunteers(): List<List<String>> {
        val db = this.readableDatabase
        val volunteersList = mutableListOf<List<String>>()

        val cursor = db.rawQuery("Select Vname, Program from Volunteer WHERE VolunteerStatus='Approved'", null)

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex("Vname"))
            val program = cursor.getString(cursor.getColumnIndex("Program"))
            val volunteerDetails = listOf(name, program)
            volunteersList.add(volunteerDetails)
        }

        cursor.close()
        db.close()

        return volunteersList
    }

    fun insertMessage(
        Firstname: String,
        Lastname: String,
        PhoneNum : String,
        Email: String,
        Message: String,
        MessageStatus: String
    ): Boolean {
        val MyDatabase = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("Firstname", Firstname)
            put("Lastname", Lastname)
            put("PhoneNum", PhoneNum)
            put("Email", Email)
            put("Message", Message)
            put("MessageStatus", MessageStatus)
        }
        val result = MyDatabase.insert("Messages", null, contentValues)
        MyDatabase.close()
        return result != -1L
    }
    companion object {
        const val databaseName = "Sign.db"
    }
}