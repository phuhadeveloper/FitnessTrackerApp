package com.example.fitnesstrackerapp.ui.datadisplay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fitnesstrackerapp.Routes
import com.example.fitnesstrackerapp.TopAppBarCustom

@Composable
fun GeneralDisplay(
    canNavigateBack: Boolean,
    navigate: () -> Unit,
    navigateToForm: () -> Unit,
    title: String,
    route: String,
    modifier: Modifier = Modifier
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
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(text = title)
                    Spacer(modifier = modifier.weight(1f))
                    IconButton(onClick = navigateToForm) {
                        Text(text = "Add Data")
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "forward arrow"
                        )
                    }
                }

                if (route == Routes.STEPS) {
                    StepsDataDisplay()
                }

                if (route == Routes.DISTANCE) {
                    DistanceDataDisplay()
                }

                if (route == Routes.CALORIES) {
                    CaloriesDataDisplay()
                }
            }
        }
    }

}