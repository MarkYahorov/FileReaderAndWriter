package com.example.filereaderandwriter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup

class SettingsActivity : AppCompatActivity() {

    private lateinit var setTextSize: RadioGroup
    private lateinit var setColor: RadioGroup
    private lateinit var set20Dp: RadioButton
    private lateinit var set30Dp: RadioButton
    private lateinit var set40Dp: RadioButton
    private lateinit var setRed: RadioButton
    private lateinit var setBlue: RadioButton
    private lateinit var setGreen: RadioButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initAll()
    }

    private fun initAll() {
        setTextSize = findViewById(R.id.set_size_text)
        setColor = findViewById(R.id.set_color_text)
        set20Dp = findViewById(R.id.radioButton)
        set30Dp = findViewById(R.id.radioButton2)
        set40Dp = findViewById(R.id.radioButton3)
        setRed = findViewById(R.id.radioButton4)
        setBlue = findViewById(R.id.radioButton5)
        setGreen = findViewById(R.id.radioButton6)
    }

    override fun onPause() {
        super.onPause()
        saveTextSizeInSharedPref()
        saveColorInSharedPref()
    }

    private fun saveTextSizeInSharedPref() {
        when {
            set20Dp.isChecked -> {
                val size = resources.getDimension(R.dimen.text_size_of_20_dp)
                getSharedPreferences("SHARED", MODE_PRIVATE).edit().apply{
                putFloat("SIZE", size)
            }.apply()
            }
            set30Dp.isChecked -> {
                val size = resources.getDimension(R.dimen.text_size_of_30_dp)
                getSharedPreferences("SHARED", MODE_PRIVATE).edit().apply{
                    putFloat("SIZE", size)
                }.apply()
            }
            set40Dp.isChecked -> {
                val size = resources.getDimension(R.dimen.text_size_of_40_dp)
                getSharedPreferences("SHARED", MODE_PRIVATE).edit().apply{
                    putFloat("SIZE", size)
                }.apply()
            }
        }
    }
    private fun saveColorInSharedPref() {
        when {
            setRed.isChecked -> {
                val color = resources.getColor(R.color.red)
                getSharedPreferences("SHARED", MODE_PRIVATE).edit().apply{
                    putInt("COLOR", color)
                }.apply()
            }
            setBlue.isChecked -> {
                val color = resources.getColor(R.color.blue)
                getSharedPreferences("SHARED", MODE_PRIVATE).edit().apply{
                    putInt("COLOR", color)
                }.apply()
            }
            setGreen.isChecked -> {
                val color = resources.getColor(R.color.green)
                getSharedPreferences("SHARED", MODE_PRIVATE).edit().apply{
                    putInt("COLOR", color)
                }.apply()
            }
        }
    }
}