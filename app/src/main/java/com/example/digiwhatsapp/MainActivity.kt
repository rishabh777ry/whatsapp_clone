package com.example.digiwhatsapp

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        val chats = findViewById<ImageView>(R.id.chat)
        val status = findViewById<ImageView>(R.id.status)
        val calls = findViewById<ImageView>(R.id.call)

        val icons = listOf(chats, status, calls)

        // Default selection
        chats.isSelected = true

        fun selectTab(index: Int) {
            icons.forEach { it.isSelected = false }
            icons[index].isSelected = true
            viewPager.currentItem = index
        }

        chats.setOnClickListener { selectTab(0) }
        status.setOnClickListener { selectTab(1) }
        calls.setOnClickListener { selectTab(2) }


        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectTab(position)
            }
        })
    }
}