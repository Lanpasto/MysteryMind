package com.example.mysterymind.services.analyticActivity

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mysterymind.R
import com.example.mysterymind.controllerTest.classofscreen.AnalyticActivity
import com.example.mysterymind.controllerTest.splashLoadScreen.CustomSpinner
import com.example.mysterymind.data.AppDatabase
import com.example.mysterymind.model.dao.NameDao
import com.example.mysterymind.model.dao.ZodiacDao
import com.example.mysterymind.model.entity.NameSignCompatibility
import com.example.mysterymind.model.entity.ZodiacSignCompatibility
import com.example.mysterymind.validation.Validation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnalyticProcessor(private val activity: AnalyticActivity) {
    private var maleInfoProcessor: MaleInfoProcessor
    private  var femaleInfoProcessor: FemaleInfoProcessor
    private  var nameDao: NameDao
    private  var zodiacDao: ZodiacDao


    init {
        // Ініціалізація nameDao
        val database = AppDatabase.getInstance(activity)
        nameDao = database.nameDao()

        // Ініціалізація zodiacDao
        zodiacDao = database.zodiacDao()

        // Ініціалізація об'єктів для обробки бізнес-логіки
        maleInfoProcessor = MaleInfoProcessor(nameDao, zodiacDao)
        femaleInfoProcessor = FemaleInfoProcessor(nameDao, zodiacDao)
    }

    fun updateTextView() {
        val validation = Validation()
        val maleNameEditText = activity.findViewById<EditText>(R.id.male_name_edittext)
        val femaleNameEditText = activity.findViewById<EditText>(R.id.female_name_edittext)

        val maleName = maleNameEditText.text.toString()
        val femaleName = femaleNameEditText.text.toString()

        if (validation.validateNames(maleName, femaleName)) {
            // Perform actions for valid names
            updateTextView(maleName, femaleName)
        } else {
            // Display error message
            if (maleName.isEmpty()) {
                maleNameEditText.error = "Enter the male name"
            }
            if (femaleName.isEmpty()) {
                femaleNameEditText.error = "Enter the female name"
            }
            return  // Stop execution if any field is empty
        }




    val maleZodiac = (activity.maleZodiacSpinner.selectedItem as CustomSpinner.SpinnerItem).text
        val femaleZodiac = (activity.femaleZodiacSpinner.selectedItem as CustomSpinner.SpinnerItem).text

        CoroutineScope(Dispatchers.IO).launch {
            val maleCompatibility = maleInfoProcessor.getCompatibility(maleName)
            val femaleCompatibility = femaleInfoProcessor.getCompatibility(femaleName)
            val maleZodiacCompatibility = maleInfoProcessor.getZodiacSignCompatibility(maleZodiac)
            val femaleZodiacCompatibility = femaleInfoProcessor.getZodiacSignCompatibility(femaleZodiac)

            // Calculate the overall compatibility
            val overallCompatibility = calculateOverallCompatibility(
                maleCompatibility,
                maleZodiacCompatibility,
                femaleCompatibility,
                femaleZodiacCompatibility
            )

            withContext(Dispatchers.Main) {
                // Update the text view
                activity.setTextView4Text(overallCompatibility.toInt().toString())
                activity.setProgressBarProgress(overallCompatibility)
            }
        }
    }
    private fun updateTextView(maleName: String, femaleName: String) {
        val maleNameTextView = activity.findViewById<TextView>(R.id.male_name_edittext)
        val femaleNameTextView = activity.findViewById<TextView>(R.id.female_name_edittext)

        maleNameTextView.text = maleName
        femaleNameTextView.text = femaleName
        Log.d("AnalyticProcessor", "TextView updated: maleName=$maleName, femaleName=$femaleName")

    }
    private fun calculateOverallCompatibility(
        maleCompatibility: NameSignCompatibility?,
        maleZodiacCompatibility: List<ZodiacSignCompatibility>,
        femaleCompatibility: NameSignCompatibility?,
        femaleZodiacCompatibility: List<ZodiacSignCompatibility>
    ): Float {
        var maleCompatibilitySum = 0.0f
        var femaleCompatibilitySum = 0.0f

        // Розраховуємо суму сумісності для чоловіка
        maleCompatibility?.compatibility?.toFloat()?.let { maleCompat ->
            maleCompatibilitySum += maleCompat
        }

        maleZodiacCompatibility.forEach { zodiacCompat ->
            maleCompatibilitySum += getCompatibilityValue(zodiacCompat)
        }

        // Розраховуємо суму сумісності для жінки
        femaleCompatibility?.compatibility?.toFloat()?.let { femaleCompat ->
            femaleCompatibilitySum += femaleCompat
        }

        femaleZodiacCompatibility.forEach { zodiacCompat ->
            femaleCompatibilitySum += getCompatibilityValue(zodiacCompat)
        }

        // Обчислюємо загальну сумісність
        val overallCompatibility = (maleCompatibilitySum + femaleCompatibilitySum) / 2
        Log.d("AnalyticProcessor", "Overall compatibility calculated: overallCompatibility=$overallCompatibility")

        return overallCompatibility / 2
    }

    // Function to get the compatibility status
    private fun getCompatibilityStatus(
        compatibility: Float,
        maleName: String,
        maleZodiac: String,
        femaleName: String,
        femaleZodiac: String
    ): String {
        return when (compatibility) {
            in 0.0f..50.0f -> "Враховуючи, що сумісність $femaleName і $maleName  знаходиться в діапазоні від 0.0% до 50.0%, " +
                    "можна сказати, що їм треба працювати для досягнення здорових стосунків. Це означає, що вони можуть " +
                    "гармонійно спілкуватися, знайти спільні інтереси і " +
                    "розуміти один одного. Їх взаємодія може бути приємною та збагачувати їхні життя.\n" +

                    "Звичайно, важливо пам'ятати, що сумісність $femaleName і $maleName - це складний процес, і цей прогноз базується на вказаних даних. " +
                    "Реальна сумісність може бути індивідуальною та залежати від багатьох інших факторів, таких " +
                    "як спільні цілі, комунікація та підтримка один одного."


            in 51.0f..75.0f -> "Враховуючи, що сумісність $femaleName і $maleName знаходиться в діапазоні від 51.0% до 75.0%, " +
                    "можна сказати, що вони мають нормальну сумісність для стосунків. Це означає, що вони мають певні відмінності " +
                    "та різниці в своїх характерах та інтересах, але з деякими зусиллями вони можуть знайти " +
                    "спільну мову та зрозуміння. Їхні взаємодії можуть бути варіативними та вимагати певного " +
                    "компромісу.\n" +

                    "Звичайно, важливо пам'ятати, що сумісність $femaleName і $maleName - це складний процес, " +
                    "і цей прогноз базується на вказаних даних. Реальна сумісність може бути індивідуальною " +
                    "та залежати від багатьох інших факторів, таких як спільні цілі, комунікація та підтримка " +
                    "один одного."

            in 76.0f..100.0f ->
                "За умови, що сумісність $femaleName і $maleName перебуває в діапазоні від 76.0% до 100.0%, " +
                        "можна стверджувати, що вони мають \"сильний потенціал для\" почутів. Це означає, що вони добре доповнюють " +
                        "один одного, мають спільні цінності, інтереси та багато спільного. Вони можуть взаємодіяти " +
                        "без зайвих труднощів та мають потенціал для глибокого розуміння та злагоди в стосунках.\n" +

                        "Звичайно, важливо пам'ятати, що сумісність $femaleName і $maleName - це складний процес, " +
                        "і цей прогноз базується на вказаних даних. Реальна сумісність може бути індивідуальною " +
                        "та залежати від багатьох інших факторів, таких як спільні цілі, комунікація та підтримка " +
                        "один одного."

            else -> "Не відомо"
        }
    }



    private fun getCompatibilityValue(compatibility: ZodiacSignCompatibility): Float {
        val value = compatibility.compatibility.toFloat()
        Log.d("AnalyticProcessor", "Compatibility value calculated: value=$value")

        return value
    }


    fun showInputDialog() {
        val maleName = activity.maleNameEditText.text.toString()
        val maleZodiac = activity.maleZodiacSpinner.selectedItem.toString()
        val femaleName = activity.femaleNameEditText.text.toString()
        val femaleZodiac = activity.femaleZodiacSpinner.selectedItem.toString()
        val compatibility = activity.textView4.text.toString().toFloat()
        val dialogView: View = LayoutInflater.from(activity).inflate(R.layout.dialog_input, null)
        val textView = dialogView.findViewById<View>(R.id.textView) as TextView

        // Set the desired text to display in the TextView
        val textToShow = getCompatibilityStatus(
            compatibility, maleName, maleZodiac, femaleName, femaleZodiac
        )
        textView.text = textToShow

        val alertDialogBuilder = AlertDialog.Builder(activity, R.style.TransparentDialogTheme)
        alertDialogBuilder.setView(dialogView)

        val alertDialog = alertDialogBuilder.create()

        alertDialog.setOnShowListener {
            val window = alertDialog.window
            val layoutParams = window?.attributes
            layoutParams?.width = 800
            layoutParams?.height = WindowManager.LayoutParams.WRAP_CONTENT
            window?.attributes = layoutParams
        }

        alertDialog.setCanceledOnTouchOutside(true) // Дозволяє закрити діалог при будь-якому кліку на ньому

        val shareButton = dialogView.findViewById<Button>(R.id.shareButton)
        shareButton.setOnClickListener {
            shareInformation(maleName,femaleName,compatibility)
        }

        alertDialog.show()
        Log.d("AnalyticProcessor", "InputDialog shown")

    }

    private fun shareInformation(maleName: String, femaleName: String,compatibility:Float) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Вся правда викрилась! Ми з моєю половинкою набрали ${compatibility.toInt()} бали в додатку MysteryMind" +
                    "Не відставай $maleName та $femaleName скачуйте і ви щоб не прислуховуватися до своїх сердець."
        )
        activity.startActivity(Intent.createChooser(shareIntent, "Поділитися за допомогою"))
        Log.d("AnalyticProcessor", "Information shared: maleName=$maleName, femaleName=$femaleName, compatibility=$compatibility")

    }
}

