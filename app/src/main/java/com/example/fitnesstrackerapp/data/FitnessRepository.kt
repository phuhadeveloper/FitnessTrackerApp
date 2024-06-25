package com.example.fitnesstrackerapp.data

import kotlinx.coroutines.flow.Flow
import java.util.Date

interface FitnessRepository {
    suspend fun insertSteps(steps: Steps)
    suspend fun updateSteps(steps: Steps)
    fun getAllSteps(): Flow<List<Steps>>
    suspend fun getStepsByDate(date: Date): Steps?

    fun getStepsByDateRange(startDate: Date, endDate: Date): Flow<List<Steps>>

}