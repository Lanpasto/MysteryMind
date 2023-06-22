package com.example.mysterymind.controller.classofscreen

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.mysterymind.R
import com.example.mysterymind.data.AppDatabase


class MainActivity : AppCompatActivity() {
    private lateinit var appDatabase: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "mysterymind"
        ).build()


        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)


        val animation = AnimationUtils.loadAnimation(this, R.anim.translate)

        button1.setOnClickListener {
            button1.startAnimation(animation)
            val intent = Intent(this, SimilarActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            button2.startAnimation(animation)
            val intent = Intent(this, KarmaQuestActivity::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            button3.startAnimation(animation)
            val intent = Intent(this, AnalyticActivity::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener {
            button4.startAnimation(animation)
            val intent = Intent(this, HoroscopeActivity::class.java)
            startActivity(intent)
        }
    }
}

