package com.varickq.mydemo.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.varickq.mydemo.R
import com.varickq.mydemo.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoroutineBinding

    var isCounting = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.startProgress.setOnClickListener {
            if (!isCounting) {
                isCounting = true
                startCount()
            } else {
                Toast.makeText(this, R.string.doing, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startCount() {
        binding.startProgress.text = getString(R.string.doing)
        binding.progressCircular.progress = 0
        lifecycleScope.launch {
            var count = 0
            while (count < 100) {
                count = getCount(count)
                println("test log $count")
                binding.progressCircular.progress = count
                binding.progressTv.text = "$count%"
            }
            countFinish()
        }
    }

    private fun countFinish() {
        binding.startProgress.text = getString(R.string.start)
        isCounting = false
        println("test log count finish.")
    }

    private suspend fun getCount(count: Int): Int = withContext(Dispatchers.Default) {
        if (count >= 100) return@withContext 100
        else {
            Thread.sleep(100)
            return@withContext count + 1
        }
    }
}