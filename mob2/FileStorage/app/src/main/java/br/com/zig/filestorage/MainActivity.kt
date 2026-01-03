package br.com.zig.filestorage

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    private val filename = "myFile.txt"
    private lateinit var editText: EditText
    private lateinit var btnSave: Button
    private lateinit var tvContent: TextView
    override fun onCreate(
        savedInstanceState:
        Bundle?
    ) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        btnSave = findViewById(R.id.btnSave)
        tvContent = findViewById(R.id.tvContent)
        val savedText = fileRead()
        if (savedText.isNotEmpty())
            tvContent.text = savedText
    }

    fun saveToFile(view: View) {
        val inputText = editText.text.toString()
        openFileOutput(filename, MODE_PRIVATE).use {
            it.write(inputText.toByteArray())
        }
        tvContent.text = inputText
    }

    private fun fileRead(): String {
        return if (File(filesDir, filename).exists()) {
            openFileInput(filename).bufferedReader().useLines { lines ->
                lines.fold("") { some, text -> "$some\n$text" }
            }
        } else {
            ""
        }
    }
}