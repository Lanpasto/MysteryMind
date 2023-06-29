package com.example.mysterymind.controllerTest.classofscreen

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mysterymind.R
import com.example.mysterymind.services.karmaQuestActivity.KarmaQuestManager
import com.example.mysterymind.services.karmaQuestActivity.ProgressBarManager
import kotlinx.coroutines.launch

class KarmaQuestActivity : AppCompatActivity() {
    private lateinit var karmaQuestManager: KarmaQuestManager
    private lateinit var progressBarManager: ProgressBarManager
    private lateinit var assignmentText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_task)
        assignmentText = findViewById(R.id.assignmentText)
        val karmaLevelProgressBar = findViewById<ProgressBar>(R.id.karma_level_seekbar)
        val karmaLevelPercent = findViewById<TextView>(R.id.karma_level_percent)
        progressBarManager = ProgressBarManager(karmaLevelProgressBar, karmaLevelPercent, this)
        karmaQuestManager = KarmaQuestManager(
            this,
            progressBarManager
        )

        karmaQuestManager.restoreTextFromSharedPreferences(this, assignmentText, "saved_text")

        lifecycleScope.launch {
            karmaQuestManager.initializeViews(this@KarmaQuestActivity)
        }

        Log.d("KarmaQuestActivity", "Activity created")
    }
}





