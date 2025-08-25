package com.rl.habitdot.di

import android.content.Context
import androidx.room.Room
import com.rl.habitdot.data.local.dao.HabitDao
import com.rl.habitdot.data.local.db.HabitDatabase
import com.rl.habitdot.data.repository.HabitRepositoryImpl
import com.rl.habitdot.domain.repository.HabitRepository
import com.rl.habitdot.domain.usecase.DeleteHabitUseCase
import com.rl.habitdot.domain.usecase.GetHabitsUseCase
import com.rl.habitdot.domain.usecase.HabitUseCase
import com.rl.habitdot.domain.usecase.InsertHabitUseCase
import com.rl.habitdot.domain.usecase.UpdateHabitUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HabitDatabase {
        return Room.databaseBuilder(
            context,
            HabitDatabase::class.java,
            "habit_db"
        ).build()
    }

    @Provides
    fun provideHabitDao(db: HabitDatabase): HabitDao = db.habitDao()

    @Provides
    fun provideHabitRepository(habitDao: HabitDao): HabitRepository = HabitRepositoryImpl(habitDao)

    @Provides
    fun provideHabitUseCase(habitRepository: HabitRepository): HabitUseCase {
        return HabitUseCase(
            getHabits = GetHabitsUseCase(habitRepository),
            insertHabit = InsertHabitUseCase(habitRepository),
            updateHabit = UpdateHabitUseCase(habitRepository),
            deleteHabit = DeleteHabitUseCase(habitRepository)
        )
    }
}