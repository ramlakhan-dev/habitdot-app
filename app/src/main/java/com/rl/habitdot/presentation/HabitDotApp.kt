package com.rl.habitdot.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rl.habitdot.R
import com.rl.habitdot.domain.model.Habit
import com.rl.habitdot.presentation.components.BottomSheet
import com.rl.habitdot.presentation.components.DeleteHabitDialog
import com.rl.habitdot.presentation.screens.home.Home
import com.rl.habitdot.presentation.viewmodel.HabitViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitDotApp() {
    val habitViewModel: HabitViewModel = hiltViewModel()

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var habitToEdit by remember { mutableStateOf<Habit?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var habitToDelete by remember { mutableStateOf<Habit?>(null) }

    if (showDeleteDialog && habitToDelete != null) {
        DeleteHabitDialog(
            modifier = Modifier
                .padding(16.dp),
            onConfirm = {
                habitViewModel.deleteHabit(habitToDelete!!)
                showDeleteDialog = false
            },
            onDismiss = {
                showDeleteDialog = false
            }
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            BottomSheet(
                modifier = Modifier.padding(16.dp),
                habitToEdit = habitToEdit,
                onSave = { habit ->
                    if (habit.id == 0) {
                        habitViewModel.addHabit(habit)
                    } else {
                        habitViewModel.updateHabit(habit)
                    }
                    showBottomSheet = false
                }
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.habits),
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    habitToEdit = null
                    showBottomSheet = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add)
                )
            }
        }
    ) { innerPadding ->
        Home(
            modifier = Modifier.padding(innerPadding),
            habitViewModel = habitViewModel,
            onHabitClick = { habit ->
                habitToEdit = habit
                showBottomSheet = true
            },
            onHabitLongPress = { habit ->
                habitToDelete = habit
                showDeleteDialog = true
            }
        )
    }
}