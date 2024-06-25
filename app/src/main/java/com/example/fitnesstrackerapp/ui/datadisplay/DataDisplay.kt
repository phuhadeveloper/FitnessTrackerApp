package com.example.fitnesstrackerapp.ui.datadisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstrackerapp.Routes
import com.example.fitnesstrackerapp.data.Steps
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

// constants needed to calculate distance from step counts
const val AVERAGE_STEP_SIZE = 2.5
const val FEET_IN_A_MILE = 5280

// cosntant needed to calculate calories burned from step counts
const val CAL_BURNED_PER_STEP = 0.04

@Composable
fun DataDisplay(
    modifier: Modifier = Modifier,
    data: List<Steps>,
    route: String
) {

    if (data.isEmpty()) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Your log is empty.", fontSize = 20.sp)
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        items(
            items = data,
            key = {steps: Steps -> steps.id}
        ) {steps ->

            var valueDisplay = steps.stepsCount.toDouble()

            // if the route is distance, calculate distance from stepCounts, distance = steps*(lengthOfAverageStepSize)
            if (route == Routes.DISTANCE) {
                valueDisplay = (steps.stepsCount * AVERAGE_STEP_SIZE) / FEET_IN_A_MILE
            }

            if (route == Routes.CALORIES) {
                valueDisplay = steps.stepsCount.toDouble() * CAL_BURNED_PER_STEP
            }

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
                Text(modifier = modifier.padding(10.dp), text = "${if (route == Routes.STEPS) Math.round(valueDisplay) else valueDisplay} steps")
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