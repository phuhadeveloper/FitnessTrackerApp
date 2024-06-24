package com.example.fitnesstrackerapp.ui.inputform

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstrackerapp.Routes
import com.example.fitnesstrackerapp.data.Calories
import com.example.fitnesstrackerapp.data.Distance
import com.example.fitnesstrackerapp.data.FitnessRepositoryImpl
import com.example.fitnesstrackerapp.data.Steps
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

class InputFormViewModel(
    private val repositoryImpl: FitnessRepositoryImpl
) : ViewModel() {
    // variables needed by input form
    var data = mutableStateOf("")

    var pickedDate = mutableStateOf(LocalDate.now())

    val formatDate = derivedStateOf {
        DateTimeFormatter
            .ofPattern("MMM dd yyyy")
            .format(pickedDate.value)
    }

    suspend fun enterData(
        route: String
    ) = viewModelScope.launch {
        val chosenDate: Date = Date.from(pickedDate.value.atStartOfDay().atZone(
            ZoneId.systemDefault()).toInstant())

        if (route == Routes.STEPS_FORM) {
            val steps = repositoryImpl.getStepsByDate(chosenDate)

            if (steps != null) {
                steps.stepsCount = data.value.toInt()
                repositoryImpl.updateSteps(steps)
            } else {
                repositoryImpl.insertSteps(Steps(date = chosenDate, stepsCount = data.value.toInt()))
            }
        }

        if (route == Routes.DISTANCE_FORM) {
            val distance = repositoryImpl.getDistanceByDate(chosenDate)

            if (distance != null) {
                distance.distanceCount = data.value.toDouble()
                repositoryImpl.updateDistance(distance)
            } else {
                repositoryImpl.insertDistance(Distance(date = chosenDate, distanceCount = data.value.toDouble()))
            }
        }

        if (route == Routes.CALORIES_FORM) {
            val calories = repositoryImpl.getCaloriesByDate(chosenDate)

            if (calories != null) {
                calories.caloriesCount = data.value.toDouble()
                repositoryImpl.updateCalories(calories)
            } else {
                repositoryImpl.insertCalories(Calories(date = chosenDate, caloriesCount = data.value.toDouble()))
            }
        }

        data.value = ""
        pickedDate.value = LocalDate.now()

    }
}