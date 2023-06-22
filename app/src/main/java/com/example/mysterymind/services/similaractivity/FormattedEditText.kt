package com.example.mysterymind.services.similaractivity

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.widget.EditText

class FormattedEditText(val editText: EditText) {
    init {
        editText.keyListener = DigitsKeyListener.getInstance("0123456789/")
        editText.filters = arrayOf(InputFilter.LengthFilter(21))
    }

    fun setupFormatting() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Not used
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val formattedText = formatText(s.toString())
                editText.removeTextChangedListener(this)
                editText.setText(formattedText)
                editText.setSelection(formattedText.length)
                editText.addTextChangedListener(this)
            }
        })
    }

    private fun formatText(input: String): String {
        val digitsOnly = input.filter { it.isDigit() }
        val formattedText = buildString {
            digitsOnly.forEachIndexed { index, char ->
                if (index == 2 || index == 4) {
                    append(".")
                }
                append(char)
            }
        }
        return formattedText
    }
}


