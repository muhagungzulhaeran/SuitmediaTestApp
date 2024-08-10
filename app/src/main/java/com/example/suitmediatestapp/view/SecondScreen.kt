package com.example.suitmediatestapp.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediatestapp.R
import com.example.suitmediatestapp.databinding.ActivitySecondScreenBinding
import com.example.suitmediatestapp.view.FirstScreenActivity.Companion.EXTRA_NAME

@Suppress("DEPRECATION")
class SecondScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val name = intent.getStringExtra(EXTRA_NAME)
        binding.nameTv.text = name

        binding.chooseBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}