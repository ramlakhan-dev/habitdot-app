package com.rl.habitdot.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String? = null,
    val isCompletedToday: Boolean = false,
    val shouldNotify: Boolean = false,
    val notifyTime: String? = null
)
