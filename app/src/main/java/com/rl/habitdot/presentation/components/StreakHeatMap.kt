package com.rl.habitdot.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StreakHeatmap(
    streakDates: Set<LocalDate>,
    today: LocalDate = LocalDate.now()
) {
    val defaultDays = 365
    val latestStreakDate = streakDates.maxOrNull() ?: today
    val endDateRaw = listOf(today.plusDays(defaultDays.toLong()), latestStreakDate).maxOrNull()!!

    val startDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
    val endDate = endDateRaw.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))

    val allDates = generateSequence(startDate) { it.plusDays(1) }
        .takeWhile { !it.isAfter(endDate) }
        .toList()

    val weeks = allDates.chunked(7)

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        reverseLayout = false
    ) {
        items(weeks) { week ->
            Column {
                week.forEach { date ->
                    val isStreak = streakDates.contains(date)
                    val color = if (isStreak) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.surfaceContainerHigh
                    }

                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .padding(1.dp)
                            .background(color, RoundedCornerShape(2.dp))
                    )
                }
            }
        }
    }
}
