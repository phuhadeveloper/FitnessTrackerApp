package com.example.fitnesstrackerapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstrackerapp.TopAppBarCustom
import com.example.fitnesstrackerapp.ui.theme.FitnessTrackerAppTheme

@Composable
fun HomeScreen(
    onNavigateSteps: () -> Unit,
    onNavigateDist: () -> Unit,
    onNavigateCal: () -> Unit,
    canNavigateBack: Boolean,
    title: String,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.padding(),
        topBar = {TopAppBarCustom(title = title, canNavigateBack = canNavigateBack)}
    ) {innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
        ) {
            CardSummary(
                text = "Card for Steps",
                onNavigate =  onNavigateSteps,
                modifier = modifier.padding(10.dp)
            )
            CardSummary(
                text = "Card for Distance",
                onNavigate = onNavigateDist,
                modifier = modifier.padding(10.dp)
            )
            CardSummary(
                text = "Card for Calories",
                onNavigate = onNavigateCal,
                modifier = modifier.padding(10.dp)
            )
        }

    }

}

@Composable
fun CardSummary(
    text: String,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(2f)
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = text)
                Spacer(modifier = modifier.weight(1f))
                IconButton(
                    onClick = onNavigate
                    ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "forward arrow"
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    FitnessTrackerAppTheme {
        HomeScreen({}, {}, {}, false, "Your Fitness Tracker")
    }
}