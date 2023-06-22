package com.example.mysterymind.controller.classofscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.mysterymind.R
import com.example.mysterymind.services.karmaQuestActivity.KarmaQuestManager
import com.example.mysterymind.services.karmaQuestActivity.ProgressBarManagr
import kotlinx.coroutines.launch

class KarmaQuestActivity : AppCompatActivity() {
    private lateinit var karmaQuestManager: KarmaQuestManager
    private lateinit var progressBarManagr: ProgressBarManagr
    private lateinit var assignmentText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_task)
        assignmentText = findViewById(R.id.assignmentText)
        val karmaLevelProgressBar = findViewById<ProgressBar>(R.id.karma_level_seekbar)
        val karmaLevelPercent = findViewById<TextView>(R.id.karma_level_percent)
        progressBarManagr = ProgressBarManagr(karmaLevelProgressBar, karmaLevelPercent, this)
        karmaQuestManager = KarmaQuestManager(
            this,
            progressBarManagr
        ) // Передаємо контекст активності та progressBarManager
        karmaQuestManager.restoreTextFromSharedPreferences(this, assignmentText, "saved_text")

        lifecycleScope.launch {
            karmaQuestManager.initializeViews(this@KarmaQuestActivity)
        }
    }
}




