package com.rl.habitdot.domain.repository

import com.rl.habitdot.domain.model.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    fun getHabits(): Flow<List<Habit>>
    suspend fun insert(habit: Habit)
    suspend fun update(habit: Habit)
    suspend fun delete(habit: Habit)
}