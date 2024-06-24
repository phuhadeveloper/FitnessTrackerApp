package com.example.fitnesstrackerapp.data

import kotlinx.coroutines.flow.Flow
import java.util.Date

class FitnessRepositoryImpl(
    private val stepsDao: StepsDao,
    private val distanceDao: DistanceDao,
    private val caloriesDao: CaloriesDao
) : FitnessRepository {
    override fun getAllCalories(): Flow<List<Calories>> {
        return caloriesDao.getAllCalories()
    }

    override fun getAllDistance(): Flow<List<Distance>> {
        return distanceDao.getAllDistance()
    }

    override suspend fun insertCalories(calories: Calories) {
        caloriesDao.insert(calories)
    }

    override suspend fun insertDistance(distance: Distance) {
        distanceDao.insert(distance)
    }

    override suspend fun insertSteps(steps: Steps) {
        stepsDao.insert(steps)
    }

    override suspend fun updateCalories(calories: Calories) {
        caloriesDao.update(calories)
    }

    override suspend fun updateDistance(distance: Distance) {
        distanceDao.update(distance)
    }

    override suspend fun updateSteps(steps: Steps) {
        stepsDao.update(steps)
    }

    override fun getAllSteps(): Flow<List<Steps>> {
        return stepsDao.getAllSteps()
    }
    override suspend fun getStepsByDate(date: Date): Steps? {
        return stepsDao.getStepsByDate(date)
    }

    override suspend fun getDistanceByDate(date: Date): Distance? {
        return distanceDao.getDistanceByDate(date)
    }

    override suspend fun getCaloriesByDate(date: Date): Calories? {
        return caloriesDao.getCaloriesByDate(date)
    }




}