package com.example.bookingapp.ui.villa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookingapp.ui.villa.model.ConvenienceTag
import com.example.bookingapp.ui.villa.model.Villa
import kotlin.random.Random

class VillaViewModel : ViewModel() {
    val villa = MutableLiveData<Villa>()

    fun loadData(villaId: Int) {

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
            rate = 497,
            isStared = false,
            isFavorite = false
        )

        villa.postValue(_villa)
    }

    fun toggleFavorite() {
            val copied = villa.value!!.copy(isFavorite = !villa.value!!.isFavorite)

            villa.postValue(copied)
    }

    fun toggleStar() {
        val isStared = villa.value!!.isStared
        val starNum = villa.value!!.rate
        val copied = villa.value!!.copy()
        copied.rate = if (isStared) copied.rate - 1 else copied.rate + 1
        copied.isStared = !isStared

        villa.postValue(copied)
    }
}