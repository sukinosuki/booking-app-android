package com.example.bookingapp.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.Log
import android.view.View
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

fun crossFade(activity: Context, contentView: View?, loadingView: View? = null) {
    val shortAnimationDuration =
        activity.resources.getInteger(android.R.integer.config_shortAnimTime)

    contentView?.apply {
        alpha = 0f
        visibility = View.VISIBLE
        animate()
            .alpha(1f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(null)
    }

    loadingView?.apply {
        animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    loadingView.visibility = View.GONE
                }
            })
    }
}

suspend fun <T> toCatch(block: suspend () -> T): Pair<T?, Exception?> {
    var exception: Exception? = null
    var res: T? = null

    try {
        res = block()
    } catch (e: Exception) {
        Log.e("hanami", "toCatch: e: $e")

        // TODO: 开发环境打印
//        e.printStackTrace()
//        Log.e(TAG, "toCatch: trace size ${e.stackTrace.size}")
//        Sentry.captureException(e)

//        e.stackTrace.forEach {
//            Log.e(TAG, "toCatch: trace $it")
//        }
        exception = e
    }

    return Pair(res, exception)
}

fun <T> mRepeat(m: Int, action: (Int) -> T): List<T> {
    val list = mutableListOf<T>()
    repeat(m) {
        val t = action(it)
        list.add(t)
    }

    return list
}
