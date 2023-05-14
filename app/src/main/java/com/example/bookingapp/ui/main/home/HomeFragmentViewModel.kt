package com.example.bookingapp.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookingapp.model.HomeTab

class HomeFragmentViewModel : ViewModel() {

    val tabs = MutableLiveData(
        listOf(
            HomeTab(name = "Best", value = 1),
            HomeTab(name = "Popular", value = 2),
            HomeTab(name = "Immediate", value = 3),
            HomeTab(name = "New", value = 4),
            HomeTab(name = "Profitable", value = 5),
        )
    )
}