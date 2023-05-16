package com.example.bookingapp.ui.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookingapp.ui.main.MainActivity
import com.example.bookingapp.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntroBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }


        binding.getStartedButton.setOnClickListener {

            val intent  = Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()
        }
    }
}