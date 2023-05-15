package com.example.bookingapp.ui.villa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.bookingapp.adapter.ViewPager2FragmentStateAdapter
import com.example.bookingapp.databinding.ActivityVillaBinding
import com.example.bookingapp.ui.main.home.adapter.HotelCardPictureIndicatorAdapter
import com.example.bookingapp.ui.main.home.fragments.HotelCardPictureFragment
import com.example.bookingapp.ui.villa.adapter.ConvenienceTagAdapter

class VillaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVillaBinding

    private val villaViewModel by lazy {
        ViewModelProvider(this)[VillaViewModel::class.java]
    }

    private val convenienceTagAdapter by lazy {
        ConvenienceTagAdapter(this)
    }

    private val picturesIndicatorAdapter by lazy {
        HotelCardPictureIndicatorAdapter(this, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVillaBinding.inflate(layoutInflater).also {

            setContentView(it.root)

            it.vm = villaViewModel
            it.lifecycleOwner = this

            it.fragmentHotelItemLayout.vm = villaViewModel
            it.fragmentHotelItemLayout.lifecycleOwner = this
        }


        initView()
    }

    private fun initView() {
        villaViewModel.loadData(1)

        binding.convenienceTagRecyclerview.run {
            adapter = convenienceTagAdapter
            setHasFixedSize(true)
        }

        with(binding.fragmentHotelItemLayout) {

            favoriteImage.setOnClickListener {
                villaViewModel.toggleFavorite()
            }

            starLayout.setOnClickListener {
                villaViewModel.toggleStar()
            }

            picturesViewPager.run {
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)

                        picturesIndicatorAdapter.currentIndexChange(position)
                    }
                })
            }

            picturesIndicatorRecyclerview.run {
                adapter = picturesIndicatorAdapter
                setHasFixedSize(true)
            }
        }

        villaViewModel.villa.observe(this) { villa ->
            if (villa == null) return@observe

            if (convenienceTagAdapter.tags.isEmpty()) {
                Log.i("hanami", "initView: 初始化adapter")
                convenienceTagAdapter.setData(villa.conveniences)

                picturesIndicatorAdapter.setData(villa.pictures.size)

                // update pictures' view pager
                val pictureFragments = villa.pictures.map {
                    HotelCardPictureFragment.newInstance(it, "2")
                }

                binding.fragmentHotelItemLayout.picturesViewPager.adapter =
                    ViewPager2FragmentStateAdapter(this@VillaActivity, pictureFragments)
            }
        }
    }
}