package com.example.fitnesstrackerapp

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstrackerapp.data.FitnessDatabase
import com.example.fitnesstrackerapp.ui.datadisplay.GeneralDisplay
import com.example.fitnesstrackerapp.ui.home.HomeScreen
import com.example.fitnesstrackerapp.ui.inputform.InputForm
import com.example.fitnesstrackerapp.ui.theme.FitnessTrackerAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessTrackerAppTheme {
                val navController = rememberNavController()

                // Routes for the app
                NavHost(
                    navController = navController,
                    startDestination = Routes.HOME,
                ) {
                    composable(Routes.HOME) {
                        HomeScreen(
                            onNavigateSteps = { navController.navigate(Routes.STEPS) },
                            onNavigateDist = { navController.navigate(Routes.DISTANCE) },
                            onNavigateCal = { navController.navigate(Routes.CALORIES) },
                            canNavigateBack = false,
                            title = "Your Fitness Tracker"
                        )
                    }
                    composable(Routes.STEPS) {
                        GeneralDisplay(
                            canNavigateBack = true,
                            navigate = { navController.popBackStack() },
                            navigateToForm = { navController.navigate(Routes.STEPS_FORM) },
                            title = "Your Steps Summary",
                            route = Routes.STEPS
                        )
                    }
                    composable(Routes.DISTANCE) {
                        GeneralDisplay(
                            canNavigateBack = true,
                            navigate = { navController.popBackStack() },
                            title = "Your Distance Summary",
                            route = Routes.DISTANCE
                        )
                    }
                    composable(Routes.CALORIES) {
                        GeneralDisplay(
                            canNavigateBack = true,
                            navigate = { navController.popBackStack() },
                            title = "Your Calories Summary",
                            route = Routes.CALORIES
                        )
                    }
                    composable(Routes.STEPS_FORM) {
                        InputForm(
                            title = "Enter Your Steps Data",
                            canNavigateBack = true,
                            navigate = { navController.popBackStack() },
                        )
                    }
                }

            }
        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCustom(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigate: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigate) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "arrow back"
                    )
                }
            }
        }
    )
}