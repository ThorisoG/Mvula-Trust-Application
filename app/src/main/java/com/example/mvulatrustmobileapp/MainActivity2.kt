package com.example.mvulatrustmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var Email2 = ""
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



        // Retrieve the email from the intent
        val email = intent.getStringExtra("email")

        // Check if the email is not null
        if (email != null) {
            // Show a toast message to notify the user about the echoing email
            Toast.makeText(this, "Welcome: $email", Toast.LENGTH_SHORT).show()
            Email2 = email

        } else {
            // If the email is null, show a different toast message or handle it accordingly
            Toast.makeText(this, "No email found in the intent", Toast.LENGTH_SHORT).show()
        }

        // Inside your MainActivity2 where you replace the fragment
        val userHomeFragment = User_home.newInstance("param1", "param2").apply {
            arguments = Bundle().apply {
                putString("email", email)
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, userHomeFragment)
            .commit()

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle (this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, User_home()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, User_home()).commit()
            R.id.nav_donations -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Donation()).commit()
            R.id.nav_volunteers -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Volunteer_form()).commit()
            R.id.nav_settings -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, User_settings()).commit()
            R.id.nav_about -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, About_us()).commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
