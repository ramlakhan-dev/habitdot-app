package com.rl.habitdot.domain.usecase

import com.rl.habitdot.domain.model.Habit
import com.rl.habitdot.domain.repository.HabitRepository
import javax.inject.Inject

class DeleteHabitUseCase @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) {
        habitRepository.delete(habit)
    }
}