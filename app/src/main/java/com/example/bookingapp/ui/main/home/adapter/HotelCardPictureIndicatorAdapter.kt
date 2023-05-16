package com.example.bookingapp.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.R
import com.example.bookingapp.databinding.HotelCardPictureIndicatorLayoutBinding

class HotelCardPictureIndicatorAdapter(
    private val activity: FragmentActivity,
    private var pictureSize: Int
) : RecyclerView.Adapter<HotelCardPictureIndicatorAdapter.ViewHolder>() {

    var currentIndex = 0

    class ViewHolder(
        val binding: HotelCardPictureIndicatorLayoutBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HotelCardPictureIndicatorLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        binding.lifecycleOwner = activity
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pictureSize
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.indicator.isPressed = position == currentIndex
    }


    fun currentIndexChange(i: Int) {
        currentIndex = i
        notifyDataSetChanged()
    }

    fun setData(size: Int) {
        pictureSize = size
        notifyDataSetChanged()
    }
}