package com.example.fitnesstrackerapp.ui.inputform

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstrackerapp.Routes
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

    // method to insert/update data into database
    suspend fun enterData() = viewModelScope.launch {
        // convert date to the right data type
        val chosenDate: Date = Date.from(pickedDate.value.atStartOfDay().atZone(
            ZoneId.systemDefault()).toInstant())

        // make a call to database using the date
        val steps = repositoryImpl.getStepsByDate(chosenDate)

        // if response is empty (insert) else (update)
        if (steps != null) {
            steps.stepsCount = data.value.toInt()
            repositoryImpl.updateSteps(steps)
        } else {
            repositoryImpl.insertSteps(Steps(date = chosenDate, stepsCount = data.value.toInt()))
        }

        //reset values
        data.value = ""
        pickedDate.value = LocalDate.now()

    }
}