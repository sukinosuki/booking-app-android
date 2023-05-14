package com.example.bookingapp.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.R
import com.example.bookingapp.databinding.HotelCardPictureIndicatorLayoutBinding

class HotelCardPictureIndicatorAdapter(
    private val activity: FragmentActivity,
    private val pictureSize: Int
) : RecyclerView.Adapter<HotelCardPictureIndicatorAdapter.ViewHolder>() {

    var currentIndex = 0

    class ViewHolder(
        activity: FragmentActivity,
        val binding: HotelCardPictureIndicatorLayoutBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // TODO: adapter引用了activity
            binding.lifecycleOwner = activity
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HotelCardPictureIndicatorLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(activity, binding)
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
}