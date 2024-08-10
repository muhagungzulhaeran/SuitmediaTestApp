package com.example.suitmediatestapp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatestapp.R
import com.example.suitmediatestapp.Result
import com.example.suitmediatestapp.adapter.StateLoadingAdapter
import com.example.suitmediatestapp.adapter.UserAdapter
import com.example.suitmediatestapp.databinding.ActivityMainBinding
import com.example.suitmediatestapp.viewmodel.MainViewModel
import com.example.suitmediatestapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>(){
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager

    }

    private fun listUsers(){
        lifecycleScope.launch {
            viewModel.getusers().observe(this@MainActivity) { user ->
                when (user) {
                    is Result.Error -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        val error = user.error
                        Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                    }
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    private fun getData() {
        val adapter = UserAdapter()
        binding.rvUsers.adapter =  adapter.withLoadStateFooter(
            footer = StateLoadingAdapter {
                adapter.retry()
            }
        )
        viewModel.user.observe(this, {
            adapter.submitData(lifecycle, it)
        })
    }
}