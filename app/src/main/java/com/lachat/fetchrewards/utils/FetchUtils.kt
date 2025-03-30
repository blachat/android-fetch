package com.lachat.fetchrewards.utils

import android.content.Context
import android.content.res.ColorStateList
import androidx.appcompat.view.ContextThemeWrapper
import com.google.android.material.chip.Chip
import com.lachat.fetchrewards.R

object FetchUtils {
    fun getCardColor(context: Context, listId: Int): Int {
        return context.getColor(
            when (listId) {
                0 -> R.color.pink
                1 -> R.color.green
                2 -> R.color.blue
                3 -> R.color.purple
                4 -> R.color.yellow
                else -> R.color.orange
            }
        )
    }

    fun getGroupChip(context: Context, groupId: Int): Chip {
        val chip =  Chip(ContextThemeWrapper(context, com.google.android.material.R.style.Widget_Material3_ChipGroup))
        with(chip) {
            chipBackgroundColor = ColorStateList.valueOf(getCardColor(context, groupId))
            isClickable = true
            isCheckable = true
            isCheckedIconVisible = true
            tag = groupId
            text = context.getString(R.string.group, groupId)
        }
        return chip
    }
}