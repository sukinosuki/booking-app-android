package com.example.bookingapp.ui.villa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.databinding.ConvenienceTagLayoutBinding
import com.example.bookingapp.ui.villa.model.ConvenienceTag

class ConvenienceTagAdapter(private val activity: AppCompatActivity) :
    RecyclerView.Adapter<ConvenienceTagAdapter.ViewHolder>() {

    var tags = listOf<ConvenienceTag>()

    class ViewHolder(val binding: ConvenienceTagLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ConvenienceTagLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.lifecycleOwner = activity

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tags.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            tagText.text = tags[position].title
        }
    }

    fun setData(list: List<ConvenienceTag>) {
        this.tags = list
        notifyDataSetChanged()
    }
}