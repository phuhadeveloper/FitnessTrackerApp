package com.example.fitnesstrackerapp

import android.app.Application
import com.example.fitnesstrackerapp.data.FitnessDatabase
import com.example.fitnesstrackerapp.data.FitnessRepositoryImpl

class FitnessTrackerApp : Application() {
    private val database by lazy { FitnessDatabase.getDatabase(this) }

    val repository by lazy { FitnessRepositoryImpl(database.stepsDao(), database.distanceDao(), database.caloriesDao()) }
}