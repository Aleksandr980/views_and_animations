package com.example.views_and_animations

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.views_and_animations.databinding.ActivityMaimBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMaimBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity","MainActivity onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityMaimBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
annotation class AndroidEntryPoint

