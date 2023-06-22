package com.example.mysterymind.controller.splashLoadScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.mysterymind.controller.classofscreen.MainActivity
import com.example.mysterymind.R
import com.example.mysterymind.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val progressBar: TriangleProgressBar = findViewById(R.id.progressBar)
        progressBar.setProgress(75, 4000) // Встановлюємо прог

        val imageView = findViewById<ImageView>(R.id.imageView1)

        val scaleAnimation = ScaleAnimation(
            1f, 2f, // Початковий та кінцевий масштаб по горизонталі
            1f, 2f, // Початковий та кінцевий масштаб по вертикалі
            Animation.RELATIVE_TO_SELF, 0.5f, // Центр масштабування по горизонталі
            Animation.RELATIVE_TO_SELF, 0.5f // Центр масштабування по вертикалі
        )

        scaleAnimation.duration = 4000 // Тривалість анімації (4 секунди)
        scaleAnimation.fillAfter = true // Залишити зображення у збільшеному масштабі після анімації

        imageView.startAnimation(scaleAnimation)



        CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

}