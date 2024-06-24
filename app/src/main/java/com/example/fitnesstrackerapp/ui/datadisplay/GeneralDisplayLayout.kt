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
    navigateToForm: () -> Unit,
    title: String,
    route: String,
) {

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
                    IconButton(onClick = navigateToForm) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "forward arrow"
                        )
                    }
                }

                if (route == Routes.STEPS) {
                    StepsDataDisplay(modifier = modifier)
                }

                if (route == Routes.DISTANCE) {
                    DistanceDataDisplay(modifier = modifier)
                }

                if (route == Routes.CALORIES) {
                    CaloriesDataDisplay(modifier = modifier)
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