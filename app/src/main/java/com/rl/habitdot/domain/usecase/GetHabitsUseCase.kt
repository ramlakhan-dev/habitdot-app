package com.rl.habitdot.domain.usecase

import com.rl.habitdot.domain.model.Habit
import com.rl.habitdot.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHabitsUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    operator fun invoke(): Flow<List<Habit>> = habitRepository.getHabits()
}