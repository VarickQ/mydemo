package com.varickq.mydemo.activities

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.varickq.mydemo.R
import com.varickq.mydemo.databinding.ActivityPhotoBinding
import com.varickq.mydemo.photo.PhotoViewController
import com.varickq.mydemo.photo.adapter.PhotoViewPagerAdapter

class PhotoActivity : AppCompatActivity() {
    lateinit var binding: ActivityPhotoBinding

    private val photoViewController = PhotoViewController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val data = ArrayList<Int>()
        data.add(R.mipmap.test_img_1)
        data.add(R.mipmap.test_img_2)
        data.add(R.mipmap.test_img_3)
        binding.photoViewPager.adapter =
            PhotoViewPagerAdapter(
                data,
                photoViewController
            )
        binding.photoViewPager.setOnInterceptTouchListener(photoViewController)
        binding.photoViewPager.addOnPageChangeListener(photoViewController)
    }
}