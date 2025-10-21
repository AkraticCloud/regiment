package com.example.regiment.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.regiment.viewmodel.WorkoutViewModel
import com.example.regiment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(
    viewModel: WorkoutViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val workouts = viewModel.workouts // List<Workout>

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = { viewModel.addWorkout(
                        type = "",
                        date = "",
                        description = ""
                    )
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Workout"
                        )
                    }
                }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(workouts) { workout ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "Type: ${workout.type}")
                            Text(text = "Date: ${workout.date}")
                            Text(text = "Description: ${workout.description}")
                        }
                        IconButton(onClick = { viewModel.deleteWorkout(workout.id) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Workout"
                            )
                        }
                    }
                }
            }
        }
    }
}
