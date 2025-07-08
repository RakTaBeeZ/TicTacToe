package com.raktabeez.tictactoe

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.raktabeez.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.light_grey)
        }

        binding.imageViewo.setOnClickListener {
            var intent = Intent(this@MainActivity,PlayActivity::class.java)
            intent.putExtra("first","o")
            startActivity(intent)
        }
        binding.imageViewx.setOnClickListener {
            var intent = Intent(this@MainActivity,PlayActivity::class.java)
            intent.putExtra("first","x")
            startActivity(intent)
        }

    }

}