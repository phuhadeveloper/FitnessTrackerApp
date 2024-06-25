package com.example.fitnesstrackerapp.ui.datadisplay

import androidx.lifecycle.ViewModel
import com.example.fitnesstrackerapp.data.FitnessRepositoryImpl
import java.time.LocalDate

class GeneralDisplayViewModel(
    private val repositoryImpl: FitnessRepositoryImpl
) : ViewModel() {
    //getAllData
    val data = repositoryImpl.getAllSteps()

    //get data from last week

    val dataLastWeek = repositoryImpl.getStepsByDateRange(
        startDate = convertLocalDateToDate(LocalDate.now().minusDays(7)),
        endDate =  convertLocalDateToDate(LocalDate.now())
    )
}