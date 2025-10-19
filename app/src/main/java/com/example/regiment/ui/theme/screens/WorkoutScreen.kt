package com.example.regiment.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.regiment.viewmodel.WorkoutViewModel
import androidx.compose.material3.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.Add

/* Dependencies You Need for This: 
implementation "androidx.compose.material3:material3:1.1.2" // or latest stable
implementation "androidx.compose.material:material-icons-core:1.5.4"
implementation "androidx.compose.material:material-icons-extended:1.5.4" */

@Composable
fun WorkoutScreen(viewModel: WorkoutViewModel)
{
    var selectedType by remember { mutableStateOf("All") }
    val workouts =
    if (selectedType == "All"){ viewModel.workouts 
    }
    else { viewModel.filterWorkouts(selectedType) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Regiment") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Add an icon bar or something for a menue or an account sign in 
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Workout")
            }
        }, 
      modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
      ) {
            WorkoutFilter(selectedType) { selectedType = it }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(workouts) { workout ->
                    Card(
                        modifier = Modifier
                              .fillMaxWidth()
                              .padding(vertical = 4.dp)
                      ) {
                        Column(Modifier.padding(16.dp)) {
                            Text("Date: ${workout.date}")
                            Text("Type: ${workout.type}")
                            }
                            Button(
                                onClick = { viewModel.deleteWorkout(workout.id) },
                                modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
} 
