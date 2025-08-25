package com.rl.habitdot.data.repository

import com.rl.habitdot.data.local.dao.HabitDao
import com.rl.habitdot.data.mapper.toDomain
import com.rl.habitdot.data.mapper.toEntity
import com.rl.habitdot.domain.model.Habit
import com.rl.habitdot.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao
): HabitRepository {
    override fun getHabits(): Flow<List<Habit>> {
        return habitDao.getHabits().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun insert(habit: Habit) {
        habitDao.insert(habit.toEntity())
    }

    override suspend fun update(habit: Habit) {
        habitDao.update(habit.toEntity())
    }

    override suspend fun delete(habit: Habit) {
        habitDao.delete(habit.toEntity())
    }
}