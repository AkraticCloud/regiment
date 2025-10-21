package com.example.regiment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.regiment.ui.theme.RegimentTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.regiment.ui.theme.screens.WorkoutScreen
import com.example.regiment.viewmodel.WorkoutViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegimentTheme {
                val workoutViewModel: WorkoutViewModel = viewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WorkoutScreen(
                        viewModel = WorkoutViewModel(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    RegimentTheme {

        val dummyViewModel = WorkoutViewModel().apply {
            addWorkout("Cardio", "2025-10-19", "Running 5k")
            addWorkout("Weight", "2025-10-20", "Bench Press 3x10")
            addWorkout("Yoga", "2025-10-21", "Morning flow")
        }

        WorkoutScreen(viewModel = dummyViewModel)

    }
}
