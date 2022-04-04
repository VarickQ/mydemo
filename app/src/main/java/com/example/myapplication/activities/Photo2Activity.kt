package com.example.myapplication.activities

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPhoto2Binding
import com.example.myapplication.photo.adapter.PhotoViewPager2Adapter

class Photo2Activity : AppCompatActivity() {

    lateinit var binding: ActivityPhoto2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoto2Binding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val data = ArrayList<Int>()
        data.add(R.mipmap.test_img_1)
        data.add(R.mipmap.test_img_2)
        data.add(R.mipmap.test_img_3)

        binding.photoViewPager2.adapter = PhotoViewPager2Adapter(this, data)
        binding.photoViewPager2.currentItem = 0
        binding.photoViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }
}