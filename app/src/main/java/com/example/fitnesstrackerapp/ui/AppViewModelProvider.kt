package com.example.fitnesstrackerapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.fitnesstrackerapp.FitnessTrackerApp
import com.example.fitnesstrackerapp.ui.datadisplay.GeneralDisplayViewModel
import com.example.fitnesstrackerapp.ui.inputform.InputFormViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            InputFormViewModel(fitnessTrackerApp().repository)
        }
        initializer {
            GeneralDisplayViewModel(fitnessTrackerApp().repository)
        }
    }

    private fun CreationExtras.fitnessTrackerApp(): FitnessTrackerApp = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FitnessTrackerApp)
}