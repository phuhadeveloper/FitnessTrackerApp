package com.example.fitnesstrackerapp.ui.datadisplay

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesstrackerapp.ui.AppViewModelProvider

@Composable
fun CaloriesDataDisplay(
    modifier: Modifier = Modifier,
    repository: CaloriesViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

}