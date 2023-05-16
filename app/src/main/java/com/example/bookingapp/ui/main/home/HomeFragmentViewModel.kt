package com.example.bookingapp.ui.main.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookingapp.model.HomeTab
import com.example.bookingapp.model.Villa
import com.example.bookingapp.util.Utils
import kotlin.random.Random

class HomeFragmentViewModel : ViewModel() {

    val tabs = MutableLiveData<List<HomeTab>>(
//        listOf(
//            HomeTab(name = "Best", value = 1,),
//            HomeTab(name = "Popular", value = 2),
//            HomeTab(name = "Immediate", value = 3),
//            HomeTab(name = "New", value = 4),
//            HomeTab(name = "Profitable", value = 5),
//        )
    )
//    val text = MutableLiveData<String>(null) // TODO: 会触发observe
//    val text = MutableLiveData<String>("233") // TODO: 会触发observe
//    val text = MutableLiveData<String>() // TODO: 不会触发observe

    fun loadData() {

        val list = listOf("Best", "Popular", "Immediate", "New", "Profitable")

        val _tabs = list.mapIndexed { index, s ->
            val _vallis = mutableListOf<Villa>()
            repeat(Random.nextInt(2, 4)) {
                _vallis.add(Utils.generateRandomVilla())
            }

            HomeTab(name = s, value = index + 1, villas = _vallis)
        }

        tabs.postValue(_tabs)
    }

    fun toggleFavorite(tabIndex: Int, villaIndex: Int) {
        Log.i("hanami", "toggleFavorite: tabIndex: $tabIndex, villaIndex: $villaIndex")
        tabs.value?.let {
            val isFavorite = it[tabIndex].villas[villaIndex].isFavorite

            it[tabIndex].villas[villaIndex].isFavorite = !isFavorite

            tabs.postValue(it)
        }
    }

    fun toggleRate(tabIndex: Int, villaIndex: Int) {
        Log.i("hanami", "toggleFavorite: tabIndex: $tabIndex, villaIndex: $villaIndex")
        tabs.value?.let {
            val isRate = it[tabIndex].villas[villaIndex].isStared
            val rate = it[tabIndex].villas[villaIndex].rate

            it[tabIndex].villas[villaIndex].rate = if (isRate) rate - 1 else rate + 1

            it[tabIndex].villas[villaIndex].isStared = !isRate

            tabs.postValue(it)
        }
    }
}