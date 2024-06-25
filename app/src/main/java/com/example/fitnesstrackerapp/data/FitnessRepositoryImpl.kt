package com.example.fitnesstrackerapp.data

import kotlinx.coroutines.flow.Flow
import java.util.Date

class FitnessRepositoryImpl(
    private val stepsDao: StepsDao
) : FitnessRepository {
    override suspend fun insertSteps(steps: Steps) {
        stepsDao.insert(steps)
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

    override fun getStepsByDateRange(startDate: Date, endDate: Date): Flow<List<Steps>> {
        return stepsDao.getStepsByDateRange(startDate, endDate)
    }
}