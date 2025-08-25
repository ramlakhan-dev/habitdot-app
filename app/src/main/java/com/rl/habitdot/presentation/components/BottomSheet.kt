package com.rl.habitdot.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rl.habitdot.R
import com.rl.habitdot.domain.model.Habit
import com.rl.habitdot.utils.getCurrentTime

@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    habitToEdit: Habit? = null,
    onSave: (Habit) -> Unit
) {
    var name by remember { mutableStateOf(habitToEdit?.name ?: "") }
    var description by remember { mutableStateOf(habitToEdit?.description ?: "") }
    var shouldNotify by remember { mutableStateOf(habitToEdit?.shouldNotify ?: false) }
    var notifyTime by remember { mutableStateOf(habitToEdit?.notifyTime ?: getCurrentTime()) }
    var showTimePicker by remember { mutableStateOf(false) }

    if (showTimePicker) {
        ShowTimePicker(
            onDismiss = {
                showTimePicker = false
            },
            onTimeSelected = { time ->
                notifyTime = time
                showTimePicker = false
            }
        )
    }

    Column(
        modifier = modifier
    ) {

        Text(
            text = if (habitToEdit == null) stringResource(id = R.string.create_new_habit) else stringResource(id = R.string.edit_habit),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.name)
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.description)
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = stringResource(id = R.string.reminder),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Switch(
                checked = shouldNotify,
                onCheckedChange = {
                    shouldNotify = it
                }
            )
        }

        if (shouldNotify) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.time),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = notifyTime,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh, RectangleShape)
                        .padding(8.dp)
                        .clickable {
                            showTimePicker = true
                        }
                )

            }
        }

        Button(
            onClick = {
                if (name.isNotEmpty()) {
                    val habit = Habit(
                        name = name,
                        description = description.ifEmpty { null },
                        shouldNotify = shouldNotify,
                        notifyTime = if (shouldNotify) notifyTime else null
                    )
                    onSave(habit)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = if (habitToEdit == null) stringResource(id = R.string.create) else stringResource(id = R.string.update)
            )
        }
    }
}