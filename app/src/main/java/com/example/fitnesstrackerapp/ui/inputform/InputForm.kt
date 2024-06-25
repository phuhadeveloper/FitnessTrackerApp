package com.example.fitnesstrackerapp.ui.inputform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesstrackerapp.TopAppBarCustom
import com.example.fitnesstrackerapp.ui.AppViewModelProvider
import com.example.fitnesstrackerapp.ui.theme.FitnessTrackerAppTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputForm(
    modifier: Modifier = Modifier,
    title: String,
    canNavigateBack: Boolean,
    navigate: () -> Unit,
    repository: InputFormViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val dateDialogState = rememberMaterialDialogState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = {TopAppBarCustom(
            title = title,
            canNavigateBack = canNavigateBack,
            navigate = navigate
        )}
    ) {innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(modifier = modifier.padding(10.dp), text = "Date: ${repository.formatDate.value}")
                Spacer(modifier = modifier.weight(0.5f))
                Button(
                    onClick = { dateDialogState.show() },
                    modifier = modifier.padding(10.dp)
                ) {
                    Text(text = "Change")
                }
            }

            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text(text = "Your data")},
                value = repository.data.value,
                onValueChange = {repository.data.value = it}
            )
            Button(
                modifier = modifier.padding(10.dp),
                onClick = { coroutineScope.launch {
                    repository.enterData()
                    navigate()
                } }
            ) {
              Text(text = "Save")
            }
        }
    }
    // this is the composable foe the popup date picker
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok")
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = repository.pickedDate.value,
            title = "Pick a Date",
            allowedDateValidator = { it <= LocalDate.now() }
        ) {
            repository.pickedDate.value = it
        }
    }
}

@Preview
@Composable
fun InputFormPreview() {
    FitnessTrackerAppTheme {
        InputForm(title = "Steps", canNavigateBack = true, navigate = { /*TODO*/ })
    }
}