package com.lachat.fetchrewards.utils

import android.content.Context
import com.lachat.fetchrewards.R

object FetchUtils {
    fun getCardColor(context: Context, listId: Int): Int {
        return context.getColor(
            when (listId) {
                0 -> R.color.purple
                1 -> R.color.green
                2 -> R.color.blue
                3 -> R.color.pink
                4 -> R.color.yellow
                else -> R.color.orange
            }
        )
    }
}