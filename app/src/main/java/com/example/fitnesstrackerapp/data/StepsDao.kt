package com.example.fitnesstrackerapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface StepsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(steps: Steps)

    @Update
    suspend fun update(steps: Steps)

    @Query("SELECT * FROM `steps` WHERE date = :date")
    suspend fun getStepsByDate(date: Date): Steps?
    @Query("SELECT * FROM `steps` ORDER BY date DESC")
    fun getAllSteps(): Flow<List<Steps>>
    @Query("SELECT * FROM `steps` WHERE date BETWEEN :startDate AND :endDate")
    fun getStepsByDateRange(startDate: Date, endDate: Date): Flow<List<Steps>>
}