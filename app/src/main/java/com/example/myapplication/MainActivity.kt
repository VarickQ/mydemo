package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.myapplication.activities.CoroutineActivity
import com.example.myapplication.activities.Photo2Activity
import com.example.myapplication.activities.PhotoActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
            0
        )

        binding.photoViewCustom.setOnClickListener {
            startActivity(Intent(this, PhotoActivity::class.java))
        }

        binding.photoViewCustom2.setOnClickListener {
            startActivity(Intent(this, Photo2Activity::class.java))
        }

        binding.coroutineTest.setOnClickListener {
            startActivity(Intent(this, CoroutineActivity::class.java))
        }

        binding.matisse.setOnClickListener {
            Matisse.from(this@MainActivity)
                .choose(MimeType.ofImage())
                .countable(true)
                .maxSelectable(9)
//                .addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                .gridExpectedSize(resources.getDimensionPixelSize(R.dimen.grid_expected_size))
//                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                .thumbnailScale(0.85f)
                .imageEngine(GlideEngine())
                .forResult(1)
        }

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data != null) {
                val select = Matisse.obtainResult(it.data)
                println("on result select:$select")
            }
        }
    }
}
