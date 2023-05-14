package com.example.bookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.bookingapp.adapter.ViewPager2FragmentStateAdapter
import com.example.bookingapp.databinding.ActivityMainBinding
import com.example.bookingapp.ui.main.chats.ChatsFragment
import com.example.bookingapp.ui.main.favorite.FavoriteFragment
import com.example.bookingapp.ui.main.home.HomeFragment
import com.example.bookingapp.ui.main.settings.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        initView()
    }

    private fun initView() {

        val fragments = listOf(
            HomeFragment.newInstance(),
            FavoriteFragment.newInstance(),
            ChatsFragment.newInstance(),
            SettingsFragment.newInstance(),
        )

       binding.viewPager.run {
            adapter = ViewPager2FragmentStateAdapter(this@MainActivity, fragments)

            isUserInputEnabled = false// viewPager禁止滑动

            setCurrentItem(0, false)// 设置当前显示的 page item

            offscreenPageLimit = fragments.size // viewPager保留的item避免destroy
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            val position = when (it.itemId) {
                R.id.home -> 0
                R.id.favorite -> 1
                R.id.chats -> 2
                R.id.settings -> 3
                else -> 0
            }

//            binding.viewPager.currentItem = position
            binding.viewPager.setCurrentItem(position, false)

            true
        }
    }
}