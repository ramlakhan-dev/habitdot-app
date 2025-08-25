package com.rl.habitdot.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rl.habitdot.domain.model.Habit
import com.rl.habitdot.domain.usecase.HabitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val habitUseCase: HabitUseCase
): ViewModel() {
    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            habitUseCase.insertHabit(habit)
        }
    }
}