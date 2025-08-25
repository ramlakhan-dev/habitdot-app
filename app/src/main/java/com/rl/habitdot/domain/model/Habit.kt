package com.rl.habitdot.domain.model

data class Habit(
    val id: Int = 0,
    val name: String,
    val description: String? = null,
    val isCompletedToday: Boolean = false,
    val shouldNotify: Boolean = false,
    val notifyTime: String? = null
)
