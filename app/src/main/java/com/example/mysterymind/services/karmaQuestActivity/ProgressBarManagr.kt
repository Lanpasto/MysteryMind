package com.example.mysterymind.services.karmaQuestActivity

import android.widget.ProgressBar
import android.widget.TextView

import android.content.Context
import android.content.SharedPreferences


class ProgressBarManagr(
    private val progressBar: ProgressBar,
    private val percentTextView: TextView,
    private val context: Context
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

    private var progress: Int = 0

    init {
        // Restore the progress from SharedPreferences during initialization
        progress = sharedPreferences.getInt("progress", 0)
        setProgress(progress)
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        progressBar.progress = progress
        updateProgressText(progress)

        // Save the progress to SharedPreferences
        saveProgressToSharedPreferences(progress)
    }

    fun getProgress(): Int {
        return progress
    }

    private fun updateProgressText(progress: Int) {
        val percentText = "$progress%"
        percentTextView.text = percentText
    }

    private fun saveProgressToSharedPreferences(progress: Int) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("progress", progress)
        editor.apply()
    }
}



