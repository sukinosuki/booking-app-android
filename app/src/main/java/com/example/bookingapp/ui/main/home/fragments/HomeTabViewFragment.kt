package com.example.bookingapp.ui.main.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.adapter.ViewPager2FragmentStateAdapter
import com.example.bookingapp.databinding.FragmentHomeTabViewBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeTabViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeTabViewFragment : Fragment() {
    private lateinit var binding: FragmentHomeTabViewBinding

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

        val fragmentList = listOf<Fragment>(
            HotelCardFragment.newInstance("1", "2"),
            HotelCardFragment.newInstance("1", "2"),
            HotelCardFragment.newInstance("1", "2"),
        )

        binding.viewPager.run {
            adapter = ViewPager2FragmentStateAdapter(requireActivity(), fragmentList)
        }

//        fragmentList.forEachIndexed { index,_ ->
//            val view = binding.viewPager.getChildAt(index) as RecyclerView
//
//            view.setPadding(120,0, 0, 0)
//            view.clipToPadding = false
//        }
            val view = binding.viewPager.getChildAt(0) as RecyclerView

            view.setPadding(0,0, 160, 0)
            view.clipToPadding = false

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeTabViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: Int) =
            HomeTabViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }
}