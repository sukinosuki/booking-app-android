package com.example.bookingapp.ui.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.bookingapp.adapter.ViewPager2FragmentStateAdapter
import com.example.bookingapp.databinding.FragmentHomeBinding
import com.example.bookingapp.ui.main.home.fragments.HomeTabViewFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val homeFragmentViewModel by lazy {
        ViewModelProvider(requireActivity())[HomeFragmentViewModel::class.java]
    }

    private val homeAreaBottomSheetFragment by lazy {
//        HomeAreaBottomSheetFragment.newInstance(homeAreaBottomSheetFragmentClickListener).also {
        HomeAreaBottomSheetFragment.newInstance { text, text2 ->
            // TODO
        }.also {

//            it.setOnClickListener(HomeAreaBottomSheetFragment.OnItemClickListener { text ->
//                Log.i("hanami", "click1111: $text")
//                Toast.makeText(requireActivity(), "233 - $text", Toast.LENGTH_SHORT).show()
//            })
//            it.setOnClickListener {
//                Toast.makeText(requireActivity(), "233 - $it", Toast.LENGTH_SHORT).show()
//            }

            it.setOnClickListener {t1,t2->
//                Log.i("hanami", "aaa t1 $t1: t2 $t2")
            }
            it.setOnClickListener2 { t1,t2->
                Toast.makeText(requireActivity(), "222 - $t1", Toast.LENGTH_SHORT).show()
            }
//            it.setOnClickListener2 {
//                Toast.makeText(requireActivity(), "233 - $it", Toast.LENGTH_SHORT).show()
//            }
        }
    }

    private val homeAreaBottomSheetFragmentClickListener =
        HomeAreaBottomSheetFragment.OnItemClickListener { text, text2 ->
            Log.i("hanami", "t1 $text: t2 $text2")
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // view pager
        binding.tabViewPager.apply {
            // 设置tab改变回调
            registerOnPageChangeCallback(viewPageOnChangeCallback)

            // ViewPager2设置overScrollMode
            getChildAt(0)?.let {
                it.overScrollMode = View.OVER_SCROLL_NEVER
            }
        }

        binding.locationName.setOnClickListener { it->
            homeAreaBottomSheetFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        homeFragmentViewModel.loadData()

//        homeFragmentViewModel.text.observe(requireActivity()) {
//            Toast.makeText(requireActivity(), "$it", Toast.LENGTH_SHORT).show()
//        }

        homeFragmentViewModel.tabs.observe(requireActivity()) {
            Log.i("hanami", "onViewCreated: tabs $it")


            if (it?.isNullOrEmpty() != false) return@observe

            if (binding.tabViewPager.adapter == null) {

                val fragments = homeFragmentViewModel.tabs.value!!.mapIndexed { index, tab ->
                    HomeTabViewFragment.newInstance(index, tab.value)
                }

                binding.tabViewPager.run {
                    adapter = ViewPager2FragmentStateAdapter(requireActivity(), fragments)
                    offscreenPageLimit = fragments.size
                }

                // 通过TabLayoutMediator绑定tabLayout和viewPager2
                TabLayoutMediator(binding.tabLayout, binding.tabViewPager) { tab, position ->
                    tab.text = it[position].name
                }.attach()
            }
        }
    }

    private val viewPageOnChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

//            navEquipmentViewModel.setCurrentViewPageIndex(position)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}