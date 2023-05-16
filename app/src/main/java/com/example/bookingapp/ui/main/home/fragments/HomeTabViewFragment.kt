package com.example.bookingapp.ui.main.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.adapter.ViewPager2FragmentStateAdapter
import com.example.bookingapp.databinding.FragmentHomeTabViewBinding
import com.example.bookingapp.ui.main.home.HomeFragmentViewModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeTabViewFragment(private val tabIndex: Int) : Fragment() {
    private lateinit var binding: FragmentHomeTabViewBinding

    private val homeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeFragmentViewModel::class.java)
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeTabViewBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.tabs.observe(requireActivity()) {
            if (it.isEmpty()) return@observe

            if (binding.viewPager.adapter == null) {
                val fragmentList = it[tabIndex].villas.mapIndexed { index, _ ->
                    HotelCardFragment.newInstance(tabIndex, index)
                }

                binding.viewPager.run {
                    adapter = ViewPager2FragmentStateAdapter(requireActivity(), fragmentList)
                }

                val view = binding.viewPager.getChildAt(0) as RecyclerView

                view.setPadding(0, 0, 160, 0)
                view.clipToPadding = false
            }

        }
//        tabViewModel.villas.observe(requireActivity()) {
//            if (it.isEmpty()) returnTransition
//
//            val fragmentList = it.mapIndexed { index, _ ->
//                HotelCardFragment.newInstance(this, index, "2")
//            }
//
//            binding.viewPager.run {
//                adapter = ViewPager2FragmentStateAdapter(requireActivity(), fragmentList)
//            }
//
//            val view = binding.viewPager.getChildAt(0) as RecyclerView
//
//            view.setPadding(0, 0, 160, 0)
//            view.clipToPadding = false
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance(tabIndex: Int, value: Int) = HomeTabViewFragment(tabIndex)
    }
}