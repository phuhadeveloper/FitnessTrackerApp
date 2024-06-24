package com.example.fitnesstrackerapp.ui.datadisplay

import androidx.lifecycle.ViewModel
import com.example.fitnesstrackerapp.data.FitnessRepositoryImpl

class DistanceViewModel(
    private val repositoryImpl: FitnessRepositoryImpl
) : ViewModel() {

    val distanceData = repositoryImpl.getAllDistance()
}