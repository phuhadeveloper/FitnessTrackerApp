package com.example.fitnesstrackerapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface DistanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(distance: Distance)
    @Update
    suspend fun update(distance: Distance)
    @Query("SELECT * FROM `distance` WHERE date = :date")
    suspend fun getDistanceByDate(date: Date): Distance?
    @Query("SELECT * FROM `distance` ORDER BY date ASC")
    fun getAllDistance(): Flow<List<Distance>>
}