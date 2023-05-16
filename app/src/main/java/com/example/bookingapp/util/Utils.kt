package com.example.bookingapp.util

import com.example.bookingapp.model.Villa
import com.example.bookingapp.ui.villa.model.ConvenienceTag
import kotlin.random.Random

object Utils {

    fun generateRandomVilla(): Villa {
        val pictures = mutableListOf<String>()
        repeat(Random.nextInt(2, 5)) {
            pictures.add("url: $it")
        }
        val hostNames = listOf("Hanami", "Emori", "Mika", "Mirro", "Kanami", "Hina")
        val resortCities =
            listOf("The Arsana Estate", "Shang Hai", "Tokyo", "Osaka", "Shibuya", "Fujiyama")
        val _villa = Villa(
            pricePerMonth = Random.nextInt(100, 999),
            pictures = pictures,
            bedrooms = Random.nextInt(1, 10),
            bathNum = Random.nextInt(1, 10),
            square = Random.nextInt(100, 300),
            hostName = Random.nextInt(0, hostNames.size - 1).let {
                hostNames[it]
            },
            title = "Villa, " + Random.nextInt(0, resortCities.size - 1).let { resortCities[it] },
            conveniences = listOf(
                ConvenienceTag("Free parking"),
                ConvenienceTag("TV set"),
                ConvenienceTag("Video monitoring"),
                ConvenienceTag("Air conditioning")
            ),
            introduction = "Excellent two-storey villa with a terrace, private pool and parking spaces is located \n" +
                    "only 5 minutes from the Indian Ocean",
            rate = Random.nextInt(1, 500),
            isStared = false,
            isFavorite = true
        )

        return _villa
    }
}