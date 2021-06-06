package com.example.filereaderandwriter

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.lang.Exception

class CreateAndRedactorActivity : AppCompatActivity() {

    private lateinit var createAndRedactorText: EditText
    private val file = File("data.txt")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_and_redactor)

        initAll()
    }

    private fun initAll(){
        createAndRedactorText = findViewById(R.id.read_or_redactor_text)
    }

    override fun onStart() {
        super.onStart()
        showFile()
        setTextSizeAndTextColor()
    }

    private fun setTextSizeAndTextColor(){
        createAndRedactorText.textSize = getSharedPreferences("SHARED", MODE_PRIVATE).getFloat("SIZE", 0F)
        createAndRedactorText.setTextColor(getSharedPreferences("SHARED", MODE_PRIVATE).getInt("COLOR", 1))
    }

    private fun showFile(){
        if (getDir("data.txt", MODE_PRIVATE).exists()){
            try {
                val inputStream: FileInputStream = openFileInput(file.toString())
                val inputReader = InputStreamReader(inputStream)
                val buffer = BufferedReader(inputReader)
                val stringBuffer = StringBuffer()
                buffer.forEachLine {
                    stringBuffer.append("$it\n")
                }
                createAndRedactorText.setText(stringBuffer.toString())
                inputStream.close()
            }catch (e:FileNotFoundException){
                Toast.makeText(this, getDir("data.txt", MODE_PRIVATE).name, Toast.LENGTH_LONG).show()
            } catch (e: IOException) {
                Toast.makeText(this, "IO", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        saveTextInFile()
    }

    private fun saveTextInFile(){
            val text = createAndRedactorText.text.toString()
            try {
                val fileOutputStream = openFileOutput(file.toString(), MODE_PRIVATE)
                fileOutputStream.write(text.toByteArray())
                fileOutputStream.close()
                Toast.makeText(this, "saved ${getDir("data.txt", MODE_PRIVATE)}", Toast.LENGTH_LONG)
                    .show()
            } catch (e: FileNotFoundException) {
                Toast.makeText(this, "No file", Toast.LENGTH_LONG).show()
            } catch (e: IOException) {
                Toast.makeText(this, "IO", Toast.LENGTH_LONG).show()
            }
    }
}