package com.example.mysterymind.services.karmaQuestActivity

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mysterymind.R
import com.example.mysterymind.data.AppDatabase
import com.example.mysterymind.model.dao.KarmaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KarmaQuestManager(
    private val context: Context, private val progressBarManager: ProgressBarManager
) {
    private lateinit var karmaLevelText: TextView
    private lateinit var karmaLevelProgressBar: ProgressBar
    private lateinit var karmaLevelPercent: TextView
    private lateinit var notifyCheckboxText: TextView
    private lateinit var startButton: ImageButton
    private lateinit var refuseButton: Button
    private lateinit var completeButton: Button
    private lateinit var assignmentText: TextView
    private var karmaDao: KarmaDao
    private var clickCount = 0 // Змінна для відстеження кількості натискань кнопки

    init {
        val database = AppDatabase.getInstance(context)
        karmaDao = database.karmaDao()
    }

    suspend fun initializeViews(activity: AppCompatActivity) {
        karmaLevelText = activity.findViewById(R.id.karma_level_text)
        karmaLevelProgressBar = activity.findViewById(R.id.karma_level_seekbar)
        karmaLevelPercent = activity.findViewById(R.id.karma_level_percent)
        notifyCheckboxText = activity.findViewById(R.id.notify_checkbox_text)
        startButton = activity.findViewById(R.id.start)
        refuseButton = activity.findViewById(R.id.refuse)
        completeButton = activity.findViewById(R.id.complete)
        assignmentText = activity.findViewById(R.id.assignmentText)

        // Вимкнути кнопки refuseButton і completeButton
        refuseButton.isEnabled = false
        completeButton.isEnabled = false

        // Додайте обробники подій для кнопок, якщо потрібно
        restoreButtonStateFromSharedPreferences()

        startButton.setOnClickListener {
            clickCount = 0
            Log.d("KarmaQuestManager", "Start button clicked")
            startButton.startAnimation(
                AnimationUtils.loadAnimation(
                    context, R.anim.button_press_animation
                )
            )
            activity.lifecycleScope.launch(Dispatchers.Main) {
                // Отримати випадкове призначення в фоновому потоці
                val randomAssignment = withContext(Dispatchers.IO) {
                    karmaDao.getRandomAssignment().textOfAssignment
                }
                // Оновити UI на головному потоці
                if (!refuseButton.isEnabled) {
                    showStartDialog()
                    Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
                    // Увімкнути кнопки refuseButton і completeButton після натискання startButton
                    refuseButton.isEnabled = true
                    completeButton.isEnabled = true
                    assignmentText.text = randomAssignment
                } else {
                    Toast.makeText(context, "Stop", Toast.LENGTH_SHORT).show()
                    refuseButton.isEnabled = false
                    completeButton.isEnabled = false
                    assignmentText.text = ""
                    progressBarManager.setProgress(0)
                }
                saveTextToSharedPreferences(context, randomAssignment, "saved_text")
                saveButtonStateToSharedPreferences()
            }
        }

        refuseButton.setOnClickListener {
            Log.d("KarmaQuestManager", "Refuse button clicked")
            refuseButton.startAnimation(
                AnimationUtils.loadAnimation(
                    context, R.anim.button_press_animation
                )
            )
            activity.lifecycleScope.launch(Dispatchers.Main) {
                val randomAssignment = withContext(Dispatchers.IO) {
                    karmaDao.getRandomAssignment().textOfAssignment
                }
                assignmentText.text = randomAssignment

                // Перевірка, чи два рази підряд натиснута кнопка refuseButton
                if (clickCount == 1) {
                    showBadDialog() // Виклик функції для відображення діалогу "Successful"
                    clickCount = 0 // Скидаємо лічильник натискань
                } else {
                    clickCount++ // Збільшуємо лічильник натискань
                }

                val currentProgress = progressBarManager.getProgress()
                if (currentProgress >= 10) {
                    progressBarManager.setProgress(currentProgress - 10)
                }

                if (progressBarManager.getProgress() >= 70) {
                    completeButton.isEnabled = true
                }

                saveTextToSharedPreferences(context, randomAssignment, "saved_text")
                saveButtonStateToSharedPreferences()
            }
        }

        completeButton.setOnClickListener {
            clickCount = 0
            val currentProgress = progressBarManager.getProgress()
            if (currentProgress + 10 <= 100) {
                progressBarManager.setProgress(currentProgress + 10)
                if (progressBarManager.getProgress() >= 100) {
                    completeButton.isEnabled = false
                    Toast.makeText(context, "Successful", Toast.LENGTH_SHORT)
                        .show() // Виведення повідомлення "Successful"
                    showSuccessDialog() // Виклик методу для відображення діалогового вікна

                }
                completeButton.startAnimation(
                    AnimationUtils.loadAnimation(
                        context, R.anim.button_press_animation
                    )
                )
                Log.d("KarmaQuestManager", "Complete button clicked")
                activity.lifecycleScope.launch(Dispatchers.Main) {
                    val randomAssignment = withContext(Dispatchers.IO) {
                        karmaDao.getRandomAssignment().textOfAssignment
                    }
                    assignmentText.text = randomAssignment
                    saveTextToSharedPreferences(context, randomAssignment, "saved_text")
                    saveButtonStateToSharedPreferences()
                }
            }
        }

        Log.d("KarmaQuestManager", "Views initialized") // Логування події ініціалізації відображення
    }

    // Save the text value from TextView to SharedPreferences
    fun saveTextToSharedPreferences(context: Context, text: String, key: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putString(key, text)
        editor.apply()
    }

    // Restore the text value from SharedPreferences and set it to TextView
    fun restoreTextFromSharedPreferences(context: Context, textView: TextView, key: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

        val savedValue: String? = sharedPreferences.getString(key, "")

        textView.text = savedValue
    }

    private fun saveButtonStateToSharedPreferences() {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        editor.putBoolean("refuse_button_enabled", refuseButton.isEnabled)
        editor.putBoolean("complete_button_enabled", completeButton.isEnabled)
        editor.apply()
    }

    private fun restoreButtonStateFromSharedPreferences() {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

        val refuseButtonEnabled: Boolean =
            sharedPreferences.getBoolean("refuse_button_enabled", false)
        val completeButtonEnabled: Boolean =
            sharedPreferences.getBoolean("complete_button_enabled", false)

        // Встановити збережений стан для кнопок
        refuseButton.isEnabled = refuseButtonEnabled
        completeButton.isEnabled = completeButtonEnabled
    }

    @SuppressLint("MissingInflatedId")
    private fun showSuccessDialog() {
        val dialogBuilder = AlertDialog.Builder(context, R.style.CustomDialogStyle)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_success, null)
        dialogBuilder.setView(dialogView)

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)

        dialogTitle.text = "Ви очистили карму\nі перейшли на інший духовний рівень"

        val dialog = dialogBuilder.create()
        dialog.setCancelable(true)
        dialog.show()

        dialogView.setOnClickListener {
            refuseButton.isEnabled = false
            completeButton.isEnabled = false
            assignmentText.text = ""
            progressBarManager.setProgress(0)
            dialog.dismiss()
        }
        dialog.setOnCancelListener {
            refuseButton.isEnabled = false
            completeButton.isEnabled = false
            assignmentText.text = ""
            progressBarManager.setProgress(0)
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun showBadDialog() {
        val dialogBuilder = AlertDialog.Builder(context, R.style.TransparentDialogTheme)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_bad, null)
        dialogBuilder.setView(dialogView)

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)

        dialogTitle.text = "Не випробовуйте долю\n"

        val dialog = dialogBuilder.create()
        dialog.setCancelable(true)
        dialog.show()


        dialogView.setOnClickListener {
            dialog.dismiss()
        }
    }
    @SuppressLint("MissingInflatedId")
    private fun showStartDialog() {
        val dialogBuilder = AlertDialog.Builder(context, R.style.CustomDialogStyle)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_start, null)
        dialogBuilder.setView(dialogView)

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)

        dialogTitle.text = "Оберіть завдання залежно від можливостей і настрою. " +
                "\nЗавершіть цей процес виконання, навіть якщо зустрінете відмову. " +
                "\nСпробуйте виконати 10 завдань підряд," +
                "\nце допоможе вам знайти гармонію з собою."

        val dialog = dialogBuilder.create()
        dialog.setCancelable(true)
        dialog.show()


        dialogView.setOnClickListener {
            dialog.dismiss()
        }
    }
}
