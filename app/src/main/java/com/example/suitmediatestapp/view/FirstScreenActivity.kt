package com.example.suitmediatestapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmediatestapp.R
import com.example.suitmediatestapp.databinding.ActivityFirstScreenBinding

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkBtn.setOnClickListener {
            val palindrome = binding.sentenceInput.text.toString()
            if (isPalindrome(palindrome)){
                showDialog(this, true)
            } else {
                showDialog(this, false)
            }
        }

        binding.nextBtn.setOnClickListener {
            val name = binding.nameInput.text.toString()
            val intent = Intent(this, SecondScreen::class.java)
            intent.putExtra(EXTRA_NAME,name)
            startActivity(intent)
        }
    }

    private fun isPalindrome(string: String): Boolean {
        val stringWithoutSpaces = string.replace("\\s+".toRegex(), "").lowercase()
        return stringWithoutSpaces.reversed() == stringWithoutSpaces
    }

    private fun showDialog(context: Context, isPalindrome: Boolean) {
        val message = if (isPalindrome) {
            getString(R.string.ispalindrome)
        } else {
            getString(R.string.notpalindrome)
        }

        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.sentence))
        builder.setMessage(message)
        builder.setPositiveButton(getString(R.string.ok)) { _, _ -> }
        builder.show()
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
    }
}