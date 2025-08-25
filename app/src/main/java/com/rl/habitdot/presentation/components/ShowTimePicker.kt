package com.rl.habitdot.presentation.components

import android.annotation.SuppressLint
import android.icu.util.Calendar
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rl.habitdot.R

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTimePicker(
    onDismiss: () -> Unit,
    onTimeSelected: (String) -> Unit
) {

    val calendar = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = calendar.get(Calendar.HOUR_OF_DAY),
        initialMinute = calendar.get(Calendar.MINUTE),
        is24Hour = true
    )


    AlertDialog (

        onDismissRequest = onDismiss,
        text = {
            TimePicker(
                state = timePickerState
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val selectedTime = String.format("%02d:%02d", timePickerState.hour, timePickerState.minute)
                    onTimeSelected(selectedTime)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.ok)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = stringResource(id = R.string.cancel)
                )
            }
        }
    )
}