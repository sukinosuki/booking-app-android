package com.example.bookingapp.ui.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.bookingapp.ui.main.MainActivity
import com.example.bookingapp.databinding.ActivityIntroBinding
import kotlin.math.log

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

        val runnable = Runnable {
            Log.i("hanami", "run: runnable run ")
            val intent  = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val looper = Looper.getMainLooper()
        val handler = Handler(looper)

        handler.postDelayed(runnable, 2000)

        // TODO: ?
//        handler.removeCallbacks(runnable)
    }
}