package com.example.bookingapp.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeFragmentViewModel.tabs.observe(requireActivity()) {
            if (it.isEmpty()) return@observe

            val fragments = homeFragmentViewModel.tabs.value!!.map {
                HomeTabViewFragment.newInstance(it.name, it.value)
            }

            binding.viewPager.adapter =
                ViewPager2FragmentStateAdapter(requireActivity(), fragments)

            // 通过TabLayoutMediator绑定tabLayout和viewPager2
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = it[position].name
            }.attach()
        }

//        view pager
        binding.viewPager.apply {
            // 设置tab改变回调
            registerOnPageChangeCallback(viewPageOnChangeCallback)
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