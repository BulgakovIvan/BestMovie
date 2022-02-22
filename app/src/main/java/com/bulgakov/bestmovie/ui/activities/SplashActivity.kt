package com.bulgakov.bestmovie.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import com.bulgakov.bestmovie.R
import com.bulgakov.bestmovie.utils.MAIN_ACTIVITY_DELAY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()


        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            },
            MAIN_ACTIVITY_DELAY
        )

    }
}