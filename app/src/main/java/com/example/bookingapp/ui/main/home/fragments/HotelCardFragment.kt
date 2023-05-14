package com.example.bookingapp.ui.main.home.fragments

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.bookingapp.adapter.ViewPager2FragmentStateAdapter
import com.example.bookingapp.databinding.FragmentHotelItemLayoutBinding
import com.example.bookingapp.ui.main.home.adapter.HotelCardPictureIndicatorAdapter
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel


class HotelCardFragment : Fragment() {
    private lateinit var binding: FragmentHotelItemLayoutBinding

    private val picturesIndicatorAdapter by lazy {
        HotelCardPictureIndicatorAdapter(requireActivity(), 2)
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHotelItemLayoutBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pictureFragments = listOf(
            HotelCardPictureFragment.newInstance("1", "2"),
            HotelCardPictureFragment.newInstance("1", "2"),
        )

        val appearanceModel = ShapeAppearanceModel.builder()
//            .setTopLeftCorner(RoundedCornerTreatment())
//            .setTopLeftCornerSize(20F)
//            .setBottomLeftCorner(RoundedCornerTreatment())
//            .setBottomLeftCornerSize(20F)
            .setAllCornerSizes(40F)
//            .setRightEdge(TriangleEdgeTreatment(13F, false))
            .build()

        // 需要在textView的父级加上clipToChildren=false
        // https://blog.csdn.net/qq_14876133/article/details/122063689
        val shapeDrawable = MaterialShapeDrawable(appearanceModel)
        shapeDrawable.shadowCompatibilityMode = MaterialShapeDrawable.SHADOW_COMPAT_MODE_ALWAYS
//        shapeDrawable.initializeElevationOverlay(BaseApplication.instance)
        shapeDrawable.paintStyle = Paint.Style.FILL
//        shapeDrawable.setTint(Color.parseColor(color))

//        binding.picturesViewPager.background = shapeDrawable

        binding.picturesViewPager.run {
            adapter = ViewPager2FragmentStateAdapter(requireActivity(), pictureFragments)
            registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    picturesIndicatorAdapter.currentIndexChange(position)
                }
            })
        }

        binding.picturesIndicatorRecyclerview.run {
            adapter = picturesIndicatorAdapter
            setHasFixedSize(true)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = HotelCardFragment()
    }
}