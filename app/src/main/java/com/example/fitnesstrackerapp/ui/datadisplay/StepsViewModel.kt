package com.example.fitnesstrackerapp.ui.datadisplay

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.fitnesstrackerapp.data.FitnessRepositoryImpl

class StepsViewModel(
    private val repositoryImpl: FitnessRepositoryImpl
) : ViewModel() {

    val stepsData = repositoryImpl.getAllSteps()

}
