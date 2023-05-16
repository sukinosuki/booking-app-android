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