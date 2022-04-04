package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.activities.Photo2Activity
import com.example.myapplication.activities.PhotoActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.photoViewCustom.setOnClickListener {
            startActivity(Intent(this, PhotoActivity::class.java))
        }

        binding.photoViewCustom2.setOnClickListener {
            startActivity(Intent(this, Photo2Activity::class.java))
        }
    }
}
