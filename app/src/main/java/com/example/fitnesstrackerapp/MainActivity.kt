package com.example.fitnesstrackerapp

/**
 *author: Phu Ha
 * date: 6.20.2024
 *
 * This is file includes the navigation for the app
 * Other parts include setting up and connecting to Health Connect
 *
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.request.AggregateGroupByPeriodRequest
import androidx.health.connect.client.time.TimeRangeFilter
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstrackerapp.data.FitnessDatabase
import com.example.fitnesstrackerapp.data.Steps
import com.example.fitnesstrackerapp.ui.datadisplay.GeneralDisplay
import com.example.fitnesstrackerapp.ui.home.HomeScreen
import com.example.fitnesstrackerapp.ui.inputform.InputForm
import com.example.fitnesstrackerapp.ui.theme.FitnessTrackerAppTheme
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneId
import java.util.Date

class MainActivity : ComponentActivity() {
    // Health connect, client, permissions, and function to get permissions
    private lateinit var healthConnectClient: HealthConnectClient
    private val permissions =  setOf(
        HealthPermission.getReadPermission(StepsRecord::class)
    )
    private suspend fun hasAllPermissions(permissions: Set<String>): Boolean {
        return healthConnectClient.permissionController.getGrantedPermissions().containsAll(permissions)
    }
    private fun requestPermissionsActivityContract(): ActivityResultContract<Set<String>, Set<String>> {
        return PermissionController.createRequestPermissionResultContract()
    }

    // database instance
    private lateinit var db: FitnessDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        // set up Health connect client and permissions
        healthConnectClient =  HealthConnectClient.getOrCreate(context = this)

        // initiate db
        db = FitnessDatabase.getDatabase(applicationContext)

        // get permissions and try to make a request
        lifecycleScope.launch {
            val requestPermissionActivityContract = PermissionController.createRequestPermissionResultContract()

            val requestPermission = registerForActivityResult(requestPermissionActivityContract) {granted ->
                if (granted.containsAll(permissions)) {
                    println("success")
                }
            }


            val granted = healthConnectClient.permissionController.getGrantedPermissions()
            if (granted.containsAll(permissions)) {
                readStepsFromHealthConnect(
                    healthConnectClient,
                    LocalDateTime.now().minusDays(7),
                    LocalDateTime.now(),
                    db
                )
            } else {
                requestPermission.launch(permissions)
            }

        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessTrackerAppTheme {
                //set up the navigatio for the app
                val navController = rememberNavController()

                // Routes for the app
                NavHost(
                    navController = navController,
                    startDestination = Routes.HOME,
                ) {
                    // route for homescreen
                    composable(Routes.HOME) {
                        HomeScreen(
                            onNavigateSteps = { navController.navigate(Routes.STEPS) },
                            onNavigateDist = { navController.navigate(Routes.DISTANCE) },
                            onNavigateCal = { navController.navigate(Routes.CALORIES) },
                            canNavigateBack = false,
                            title = "Your Fitness Tracker"
                        )
                    }
                    //route for steps page
                    composable(Routes.STEPS) {
                        GeneralDisplay(
                            canNavigateBack = true,
                            navigate = { navController.popBackStack() },
                            navigateToForm = { navController.navigate(Routes.STEPS_FORM) },
                            title = "Your Steps Summary",
                            route = Routes.STEPS
                        )
                    }
                    // route for distance
                    composable(Routes.DISTANCE) {
                        GeneralDisplay(
                            canNavigateBack = true,
                            navigate = { navController.popBackStack() },
                            title = "Your Distance Summary",
                            route = Routes.DISTANCE
                        )
                    }
                    // route for calories
                    composable(Routes.CALORIES) {
                        GeneralDisplay(
                            canNavigateBack = true,
                            navigate = { navController.popBackStack() },
                            title = "Your Calories Summary",
                            route = Routes.CALORIES
                        )
                    }
                    // route for input form
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

// method to read step data from health connect
private suspend fun readStepsFromHealthConnect(
    healthConnectClient: HealthConnectClient,
    startTime: LocalDateTime,
    endTime: LocalDateTime,
    db: FitnessDatabase
) {
    val response = healthConnectClient
        .aggregateGroupByPeriod(
            AggregateGroupByPeriodRequest(
                metrics = setOf(StepsRecord.COUNT_TOTAL),
                timeRangeFilter = TimeRangeFilter.Companion.between(startTime, endTime),
                timeRangeSlicer = Period.ofDays(1)

            )
        )
    var currentDate = startTime
    for (dailyResult in response) {
        val steps = dailyResult.result[StepsRecord.COUNT_TOTAL]
        db.stepsDao().insert(
            Steps(
                date = Date.from(currentDate.atZone(ZoneId.systemDefault()).toInstant()),
                stepsCount = steps?.toInt() ?: 0
            )
        )
        currentDate = currentDate.plusDays(1)
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

