package com.example.bookingapp.model

import com.example.bookingapp.ui.villa.model.ConvenienceTag

data class Villa(
    val pricePerMonth: Int,
    val pictures: List<String>,
    val bedrooms: Int,
    val bathNum: Int,
    val square: Int,
    val hostName: String,
    val title: String,
    val conveniences: List<ConvenienceTag>,
    val introduction: String,
    var rate: Int,
    var isStared: Boolean,
    var isFavorite: Boolean
)