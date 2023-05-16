package com.example.bookingapp.ui.villa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

//            it.fragmentHotelItemLayout.vm = villaViewModel
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

            hotelCardPicturesLayout.let {
                val layoutParams = it.layoutParams
                layoutParams.height = 600

                it.layoutParams = layoutParams
            }

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

                // ViewPager2设置overScrollMode
                getChildAt(0)?.let {
                    it.overScrollMode = View.OVER_SCROLL_NEVER
                }
            }

            picturesIndicatorRecyclerview.run {
                adapter = picturesIndicatorAdapter
                setHasFixedSize(true)
            }
        }

        villaViewModel.villa.observe(this) { villa ->
            if (villa == null) return@observe

            binding.fragmentHotelItemLayout.villa = villa

            if (binding.fragmentHotelItemLayout.picturesViewPager.adapter == null) {
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