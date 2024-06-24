package com.example.fitnesstrackerapp.data

import kotlinx.coroutines.flow.Flow
import java.util.Date

interface FitnessRepository {
    suspend fun insertSteps(steps: Steps)
    suspend fun insertDistance(distance: Distance)
    suspend fun insertCalories(calories: Calories)
    suspend fun updateSteps(steps: Steps)
    suspend fun updateDistance(distance: Distance)
    suspend fun updateCalories(calories: Calories)
    fun getAllSteps(): Flow<List<Steps>>
    fun getAllDistance(): Flow<List<Distance>>
    fun getAllCalories(): Flow<List<Calories>>
    suspend fun getCaloriesByDate(date: Date): Calories?
    suspend fun getStepsByDate(date: Date): Steps?
    suspend fun getDistanceByDate(date: Date): Distance?

}