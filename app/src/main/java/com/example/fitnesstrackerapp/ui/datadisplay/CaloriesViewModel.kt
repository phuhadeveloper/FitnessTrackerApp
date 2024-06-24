package com.example.fitnesstrackerapp.ui.datadisplay

import androidx.lifecycle.ViewModel
import com.example.fitnesstrackerapp.data.FitnessRepositoryImpl

class CaloriesViewModel(
    private val repositoryImpl: FitnessRepositoryImpl
) : ViewModel() {

    val caloriesData = repositoryImpl.getAllCalories()

}