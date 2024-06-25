package com.example.fitnesstrackerapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface CaloriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(calories: Calories)
    @Update
    suspend fun update(calories: Calories)
    @Query("SELECT * FROM `calories` WHERE date = :date")
    suspend fun getCaloriesByDate(date: Date): Calories?
    @Query("SELECT * FROM `calories` ORDER BY date DESC")
    fun getAllCalories(): Flow<List<Calories>>
}