package com.example.fitnesstrackerapp.ui.datadisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesstrackerapp.data.Steps
import com.example.fitnesstrackerapp.ui.AppViewModelProvider
import com.example.fitnesstrackerapp.ui.inputform.InputFormViewModel
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

@Composable
fun StepsDataDisplay(
    modifier: Modifier = Modifier,
    repository: StepsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val data = repository.stepsData.collectAsState(initial = emptyList()).value

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        items(
            items = data,
            key = {steps: Steps -> steps.id}
        ) {steps ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(color = Color.White)
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(modifier = modifier.padding(10.dp), text = "${convertDateToLocalDate(steps.date)}")
                Spacer(modifier = modifier.weight(1f))
                Text(modifier = modifier.padding(10.dp), text = "${steps.stepsCount} steps")
            }
        }
    }
}

fun convertDateToLocalDate(date: Date): LocalDate {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
}

fun convertLocalDateToDate(localDate: LocalDate): Date {
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
}