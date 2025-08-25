package com.rl.habitdot.data.mapper

import com.rl.habitdot.data.local.entity.HabitEntity
import com.rl.habitdot.domain.model.Habit

fun HabitEntity.toDomain(): Habit = Habit(id, name, description, isCompletedToday, shouldNotify, notifyTime)

fun Habit.toEntity(): HabitEntity = HabitEntity(id, name, description, isCompletedToday, shouldNotify, notifyTime)