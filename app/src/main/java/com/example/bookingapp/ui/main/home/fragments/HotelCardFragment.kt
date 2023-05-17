package com.example.bookingapp.ui.main.home.fragments

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.bookingapp.R
import com.example.bookingapp.adapter.ViewPager2FragmentStateAdapter
import com.example.bookingapp.databinding.FragmentHomeHotelItemLayoutWrapBinding
import com.example.bookingapp.ui.main.home.HomeFragmentViewModel
import com.example.bookingapp.ui.main.home.adapter.HotelCardPictureIndicatorAdapter
import com.example.bookingapp.ui.villa.VillaActivity
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class HotelCardFragment(val tabIndex: Int, private val villaIndex: Int) : Fragment() {
    private lateinit var binding: FragmentHomeHotelItemLayoutWrapBinding

    private val picturesIndicatorAdapter by lazy {
        HotelCardPictureIndicatorAdapter(requireActivity(), 0)
    }

    private val homeViewModel by lazy {
        ViewModelProvider(requireActivity())[HomeFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeHotelItemLayoutWrapBinding.inflate(inflater)

        binding.lifecycleOwner = requireActivity()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentHotelItemLayout.picturesViewPager.run {

            // viewPager2设置过渡动画
//            setPageTransformer(DepthPageTransformer())
            val compositePageTransformer = CompositePageTransformer()
//            compositePageTransformer.addTransformer(ScaleInTransformer())
//        compositePageTransformer.addTransformer(DepthPageTransformer())
            // 设置viewPager2元素边距
            compositePageTransformer.addTransformer(
                MarginPageTransformer(
                    resources.getDimension(R.dimen.dp_8).toInt()
                )
            )
//            setPageTransformer(ScaleInTransformer())
            setPageTransformer(compositePageTransformer)
//            setPageTransformer(ZoomOutPageTransformer())

            // 滑动方向
            // viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL or ViewPager2.ORIENTATION_HORIZONTAL
            // orientation=ViewPager2.ORIENTATION_VERTICAL

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

        binding.fragmentHotelItemLayout.picturesIndicatorRecyclerview.run {
            adapter = picturesIndicatorAdapter
            setHasFixedSize(true)
        }

        binding.fragmentHotelItemLayout.favoriteImage.setOnClickListener {
            homeViewModel.toggleFavorite(tabIndex, villaIndex)
        }

        binding.fragmentHotelItemLayout.starLayout.setOnClickListener {
            homeViewModel.toggleRate(tabIndex, villaIndex)
        }

//        binding.fragmentHotelItemLayout.lifecycleOwner = requireActivity()
        binding.fragmentHotelItemLayout.layout.setOnClickListener {
            startActivity(Intent(requireActivity(), VillaActivity::class.java))
            requireActivity().overridePendingTransition(
                R.anim.slide_from_right,
                R.anim.slide_to_left
            )
        }

        homeViewModel.tabs.observe(requireActivity()) { tabs ->
            if (tabs.isEmpty()) return@observe

            val villa = tabs[tabIndex].villas[villaIndex]

            binding.fragmentHotelItemLayout.villa = villa

            if (binding.fragmentHotelItemLayout.picturesViewPager.adapter == null) {

                val pictureFragments = villa.pictures.map {
                    HotelCardPictureFragment.newInstance("1", "2")
                }

                picturesIndicatorAdapter.setData(villa.pictures.size)

                binding.fragmentHotelItemLayout.picturesViewPager.adapter =
                    ViewPager2FragmentStateAdapter(requireActivity(), pictureFragments)

//                // ViewPager2设置overScrollMode
//                binding.fragmentHotelItemLayout.picturesViewPager.getChildAt(0)?.let {
//                    it.overScrollMode = View.OVER_SCROLL_NEVER
//                }
            }
        }

        // TODO
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
    }

    companion object {
        @JvmStatic
        fun newInstance(tabIndex: Int, villaIndex: Int) =
            HotelCardFragment(tabIndex, villaIndex)
    }
}
