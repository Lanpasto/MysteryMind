package com.example.mysterymind.controller.classofscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.mysterymind.R
import com.example.mysterymind.data.AppDatabase
import com.example.mysterymind.listener.MyService
import com.example.mysterymind.services.mainActivity.MyActivity


class MainActivity : AppCompatActivity() {
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "mysterymind"
        ).build()
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
        val myActivity = MyActivity(this)
        myActivity.initializeViews()
        myActivity.setButtonClickListeners()
    }
}

