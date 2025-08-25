package com.rl.habitdot.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rl.habitdot.data.local.dao.HabitDao
import com.rl.habitdot.data.local.entity.HabitEntity


@Database(entities = [HabitEntity::class], version = 1)
abstract class HabitDatabase: RoomDatabase() {
    abstract fun habitDao(): HabitDao
}