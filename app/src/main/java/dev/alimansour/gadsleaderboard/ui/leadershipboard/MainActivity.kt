package dev.alimansour.gadsleaderboard.ui.leadershipboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import dev.alimansour.gadsleaderboard.R
import dev.alimansour.gadsleaderboard.databinding.ActivityMainBinding
import dev.alimansour.gadsleaderboard.ui.submit.SubmitActivity

/**
 * GADS Leaderboard Android Application developed by: Ali Mansour
 * Copyright © 2020 Ali Mansour. All Rights Reserved.
 * This file may not be redistributed in whole or significant part.
 * ----------------- GADS Leaderboard IS FREE SOFTWARE ------------------
 * https://www.alimansour.dev   |   dev.ali.mansour@gmail.com
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            //View pager setup
            val sectionsPagerAdapter =
                SectionsPagerAdapter(this@MainActivity, supportFragmentManager)

            val viewPager: ViewPager = viewPager
            viewPager.adapter = sectionsPagerAdapter

            //Tabs setup
            tabs.setupWithViewPager(viewPager)

            submitButton.setOnClickListener {
                val intent = Intent(this@MainActivity, SubmitActivity::class.java)
                startActivity(intent)
            }
        }
    }


}