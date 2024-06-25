package com.example.fitnesstrackerapp.ui.datadisplay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesstrackerapp.Routes
import com.example.fitnesstrackerapp.TopAppBarCustom
import com.example.fitnesstrackerapp.ui.AppViewModelProvider
import com.example.fitnesstrackerapp.ui.theme.FitnessTrackerAppTheme

@Composable
fun GeneralDisplay(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean,
    navigate: () -> Unit,
    navigateToForm: () -> Unit = {},
    title: String,
    route: String,
    repository: GeneralDisplayViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    
    val stepsData = repository.data.collectAsState(initial = emptyList()).value
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarCustom(
                title = title,
                canNavigateBack = canNavigateBack,
                navigate = navigate
            )
        }
    ) {innerPadding ->
        Card(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Your $route history",
                        modifier = modifier.padding(20.dp),
                        fontSize = 20.sp
                    )
                    Spacer(modifier = modifier.weight(1f))
                    if (route == Routes.STEPS) {
                        IconButton(onClick = navigateToForm) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "forward arrow"
                            )
                        }
                    }
                }

                var averageSteps = 0.0
                val lastWeekData = repository.dataLastWeek.collectAsState(initial = emptyList()).value

                lastWeekData.forEach {
                    averageSteps += it.stepsCount.toDouble()
                }
                averageSteps /= 7

                if (route == Routes.STEPS) {
                    DataDisplay(data = stepsData, route = route)

                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(fontSize = 20.sp, text = "Your steps average for the last 7 days is ${Math.round(averageSteps)} steps/day.")
                    }
                }

                if (route == Routes.DISTANCE) {
                    DataDisplay(data = stepsData, route = route)

                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(fontSize = 20.sp, text = "Your distance average for the last 7 days is ${averageSteps* AVERAGE_STEP_SIZE/ FEET_IN_A_MILE} miles/day.")
                    }
                }

                if (route == Routes.CALORIES) {
                    DataDisplay(data = stepsData, route = route)

                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(fontSize = 20.sp, text = "Your burned calories average for the last 7 days is ${averageSteps* CAL_BURNED_PER_STEP} calories/day.")
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun GeneralDisplayPreview() {
    FitnessTrackerAppTheme {
        GeneralDisplay(
            canNavigateBack = true,
            navigate = { /*TODO*/ },
            navigateToForm = { /*TODO*/ },
            title = "Step Counts",
            route = Routes.STEPS
        )
    }
}