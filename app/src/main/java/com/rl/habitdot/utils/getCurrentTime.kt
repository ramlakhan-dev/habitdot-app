package com.rl.habitdot.utils

import android.annotation.SuppressLint
import java.util.Calendar

@SuppressLint("DefaultLocale")
fun getCurrentTime(): String {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    return String.format(
        "%02d:%02d",
        hour, minute
    )
}