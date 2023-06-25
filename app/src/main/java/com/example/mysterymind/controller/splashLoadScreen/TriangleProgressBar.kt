package com.example.mysterymind.controller.splashLoadScreen

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class TriangleProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val progressPaint: Paint = Paint().apply {
        color = Color.rgb(33, 0, 0)
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    private var progress = 0
    private var targetProgress = 0

    private val animator: ValueAnimator = ValueAnimator()

    init {
        animator.addUpdateListener { animation ->
            progress = animation.animatedValue as Int
            invalidate()
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()

        val angle = progress / 100f * 360f

        val trianglePath = Path()
        val centerX = width / 2
        val centerY = height / 2
        val radius = (width / 2) - progressPaint.strokeWidth / 2

        val triangleCount = 3
        val rotationAngle = 360f / triangleCount

        for (i in 0 until triangleCount) {
            val startAngle = i * rotationAngle - 90f
            val endAngle = startAngle + angle

            trianglePath.reset()
            trianglePath.moveTo(centerX, centerY)
            trianglePath.lineTo(
                centerX + radius * kotlin.math.cos(Math.toRadians(startAngle.toDouble())).toFloat(),
                centerY + radius * kotlin.math.sin(Math.toRadians(startAngle.toDouble())).toFloat()
            )
            trianglePath.lineTo(
                centerX + radius * kotlin.math.cos(Math.toRadians(endAngle.toDouble())).toFloat(),
                centerY + radius * kotlin.math.sin(Math.toRadians(endAngle.toDouble())).toFloat()
            )
            trianglePath.close()

            canvas.drawPath(trianglePath, progressPaint)
        }
    }

    fun setProgress(progress: Int, duration: Long) {
        targetProgress = progress

        animator.cancel()
        animator.setIntValues(this.progress, targetProgress)
        animator.duration = duration
        animator.start()
    }
}



