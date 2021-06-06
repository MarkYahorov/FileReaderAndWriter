package com.example.filereaderandwriter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.Toast
import java.io.FileNotFoundException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var createBtn: Button
    private lateinit var readBtn: Button
    private lateinit var redactBtn: Button
    private lateinit var settingsBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAll()

    }

    private fun initAll(){
        createBtn = findViewById(R.id.create_btn)
        readBtn = findViewById(R.id.reader_btn)
        redactBtn = findViewById(R.id.redactor_btn)
        settingsBtn = findViewById(R.id.settings_btn)
    }

    override fun onStart() {
        super.onStart()
        createFileAndGoToCreateAndRedactorScreen()
        setBtnVisibility()
        goToReadScreen()
        goToScreenForRedctFile()
        goToSettingsScreen()
    }

    private fun createFileAndGoToCreateAndRedactorScreen(){
        createBtn.setOnClickListener {
            var text = ""
            try {
                val fileOutputStream = openFileOutput("data.txt", MODE_PRIVATE)
                fileOutputStream.write(text.toByteArray())
                fileOutputStream.close()
                Toast.makeText(this, "saved ${getDir("data.txt", MODE_PRIVATE)}", Toast.LENGTH_LONG).show()
            } catch (e: FileNotFoundException) {
                Toast.makeText(this, "No file", Toast.LENGTH_LONG).show()
            } catch (e: IOException) {
                Toast.makeText(this, "IO", Toast.LENGTH_LONG).show()
            }
                val intent = Intent(this, CreateAndRedactorActivity::class.java)
                startActivity(intent)
        }
    }

    private fun goToScreenForRedctFile(){
        redactBtn.setOnClickListener {
            val intent = Intent(this, CreateAndRedactorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setBtnVisibility(){
        Toast.makeText(this, "${getDir("data.txt", MODE_PRIVATE)}", Toast.LENGTH_LONG).show()
        if (getDir("data.txt", MODE_PRIVATE).exists()){
            redactBtn.visibility = VISIBLE
            readBtn.visibility = VISIBLE
            createBtn.visibility = GONE
        }
    }

    private fun goToReadScreen(){
        readBtn.setOnClickListener {
            val intent = Intent(this, ReaderFileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goToSettingsScreen(){
        settingsBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}