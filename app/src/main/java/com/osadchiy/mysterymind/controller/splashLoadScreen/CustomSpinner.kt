package com.example.mysterymind.controllerTest.splashLoadScreen

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContextCompat
import com.example.mysterymind.R

class CustomSpinner(context: Context, attrs: AttributeSet) : AppCompatSpinner(context, attrs) {

    data class SpinnerItem(val text: String, val imageResId: Int)

    private inner class CustomSpinnerAdapter(
        context: Context, items: List<SpinnerItem>
    ) : ArrayAdapter<SpinnerItem>(context, 0, items) {

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent, false)
            val imageView = view.findViewById<ImageView>(R.id.image_view)

            val spinnerItem = getItem(position) as SpinnerItem
            imageView.setImageResource(spinnerItem.imageResId)

            val textView = view.findViewById<TextView>(R.id.textView5)
            textView.text = spinnerItem.text
            textView.setPadding(16, 4, 16, 12) // Додати відступи (ліво, верх, право, низ)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f) // Змінити розмір шрифту
            textView.setTextColor(Color.BLACK) // Налаштувати колір тексту за потреби
            textView.gravity = Gravity.CENTER_VERTICAL // Вирівняти текст по центру по вертикалі

            if (position == 0) {
                val layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.addRule(RelativeLayout.BELOW, R.id.spinner)
                layoutParams.setMargins(0, 1, 0, 0) // Додайте невеликий відступ між спінером і першим елементом
                view.layoutParams = layoutParams
            } else {
                val layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(0, 0, 0, 0) // Зняти відступи для решти елементів
                view.layoutParams = layoutParams
            }

            val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.custom_spinner_background)
            view.background = backgroundDrawable

            return view
        }


        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent, false)
            val imageView = view.findViewById<ImageView>(R.id.image_view)

            val spinnerItem = getItem(position) as SpinnerItem
            imageView.setImageResource(spinnerItem.imageResId)

            val textView = view.findViewById<TextView>(R.id.textView5)
            textView.text = spinnerItem.text
            textView.setPadding(16, 4, 16, 12) // Додати відступи (ліво, верх, право, низ)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f) // Змінити розмір шрифту
            textView.setTextColor(Color.WHITE) // Налаштувати колір тексту за потреби
            textView.gravity = Gravity.CENTER_VERTICAL // Вирівняти текст по центру по вертикалі

            return view
        }

    }

    init {
        val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.custom_spinner_background)
        background = backgroundDrawable
    }

    fun setItems(items: List<SpinnerItem>) {
        val adapter = CustomSpinnerAdapter(context, items)
        setAdapter(adapter)
    }
}






