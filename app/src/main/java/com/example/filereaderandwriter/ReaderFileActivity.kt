package com.example.filereaderandwriter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import java.io.*
import java.lang.Exception

class ReaderFileActivity : AppCompatActivity() {

    private lateinit var readFileText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader_file)

        initAll()
    }

    private fun initAll(){
        readFileText = findViewById(R.id.reader_text)
    }

    override fun onStart() {
        super.onStart()
        readText()
        setTextSizeAndTextColor()
    }

    private fun setTextSizeAndTextColor(){
        readFileText.textSize = getSharedPreferences("SHARED", MODE_PRIVATE).getFloat("SIZE", 0F)
        readFileText.setTextColor(getSharedPreferences("SHARED", MODE_PRIVATE).getInt("COLOR", 1))
    }

    private fun readText(){
        if (getDir("data.txt", MODE_PRIVATE).exists()) {
            try {
                val inputStream: FileInputStream = openFileInput("data.txt")
                val inputReader = InputStreamReader(inputStream)
                val buffer = BufferedReader(inputReader)
                val stringBuffer = StringBuffer()
                var count = 0
                buffer.forEachLine {
                    val numberOfLine = count++
                    stringBuffer.append("$numberOfLine $it\n")
                }
                readFileText.text = stringBuffer.toString()
                inputStream.close()
            }catch (e:FileNotFoundException){
                Toast.makeText(this, getDir("data.txt", MODE_PRIVATE).name, Toast.LENGTH_LONG).show()
            } catch (e: IOException){
                Toast.makeText(this, "IO", Toast.LENGTH_LONG).show()
            }catch (e: Exception){
                Toast.makeText(this, "${openFileInput(getDir("data.txt", MODE_PRIVATE).toString())}", Toast.LENGTH_LONG).show()
            }
        }
    }
}