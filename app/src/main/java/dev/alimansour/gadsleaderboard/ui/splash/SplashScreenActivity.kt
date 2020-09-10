package dev.alimansour.gadsleaderboard.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import dev.alimansour.gadsleaderboard.R
import dev.alimansour.gadsleaderboard.databinding.ActivitySplashBinding
import dev.alimansour.gadsleaderboard.ui.leadershipboard.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runCatching {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            // Navigate to the main activity after 1 second
            lifecycleScope.launch {
                delay(1000)
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.onFailure { it.printStackTrace() }
    }
}