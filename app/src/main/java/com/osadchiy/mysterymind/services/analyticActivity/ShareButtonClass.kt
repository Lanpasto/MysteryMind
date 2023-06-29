package com.example.mysterymind.services.analyticActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.mysterymind.R

class ShareButtonClass : DialogFragment() {
    lateinit var shareButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_input, null)

        shareButton = dialogView.findViewById(R.id.shareButton)
        shareButton.setOnClickListener {
            shareInformation()
        }
    }

    private fun shareInformation() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Текст для поділу")
        startActivity(Intent.createChooser(shareIntent, "Поділитися за допомогою"))
    }
}
