package com.rl.habitdot.domain.usecase

data class HabitUseCase(
    val getHabits: GetHabitsUseCase,
    val insertHabit: InsertHabitUseCase,
    val updateHabit: UpdateHabitUseCase,
    val deleteHabit: DeleteHabitUseCase
)
