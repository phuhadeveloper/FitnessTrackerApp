package com.example.fitnesstrackerapp.ui.datadisplay

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesstrackerapp.data.Steps
import com.example.fitnesstrackerapp.ui.AppViewModelProvider
import com.example.fitnesstrackerapp.ui.inputform.InputFormViewModel

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
            Card(
                modifier = modifier.fillMaxWidth()
            ) {
                Row {
                    Text(text = "${steps.date}")
                    Text(text = "${steps.stepsCount} steps")
                }
            }
        }
    }
}