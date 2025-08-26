package com.rl.habitdot.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rl.habitdot.R
import com.rl.habitdot.domain.model.Habit
import com.rl.habitdot.presentation.components.HabitItem
import com.rl.habitdot.presentation.viewmodel.HabitViewModel

@Composable
fun Home(
    modifier: Modifier = Modifier,
    habitViewModel: HabitViewModel,
    onHabitClick: (Habit) -> Unit
) {

    val habitList = habitViewModel.allHabits.collectAsState(initial = emptyList()).value
    if (habitList.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = stringResource(id = R.string.no_habits),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(habitList) { habit ->

                HabitItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp,4.dp),
                    habit = habit,
                    onHabitClick = { habit ->
                        onHabitClick(habit)
                    }
                )
            }

        }
    }
}