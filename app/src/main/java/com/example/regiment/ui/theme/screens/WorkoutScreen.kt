package com.example.regiment.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.regiment.data.WorkoutCategory
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

    // Dropdown states from android studios and chat code
    var expandedMain by remember { mutableStateOf(false) }
    var expandedSub by remember { mutableStateOf(false) }

    var selectedCategory by remember { mutableStateOf<WorkoutCategory?>(null) }
    var selectedSubType by remember { mutableStateOf("") }

    // --- Predefined subtype options ---
    val weightTrainingOptions = listOf(
        "Resistance",
        "Free Weights (Dumbbells, Barbells, Kettle bells)",
        "Machines",
        "Body weight",
        "Resistance Bands"
    )

    val cardioOptions = listOf(
        "Running",
        "Cycling",
        "HILT",
        "Rowing",
        "Jump Rope"
    )

    val calisthenicsOptions = listOf(
        "Push-ups",
        "Pull-ups",
        "Squats",
        "Planks",
        "Dips"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        // future dialog popup for scheduled worrk out
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

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            //CATEGORY DROPDOWN
            ExposedDropdownMenuBox(
                expanded = expandedMain,
                onExpandedChange = { expandedMain = !expandedMain }
            ) {
                TextField(
                    value = selectedCategory?.name?.replace("_", " ") ?: "Select category",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Workout Category") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMain) },
                    modifier = Modifier.menuAnchor(type  = MenuAnchorType.PrimaryNotEditable, enabled = true)
                )

                ExposedDropdownMenu(
                    expanded = expandedMain,
                    onDismissRequest = { expandedMain = false }
                ) {
                    WorkoutCategory.values().forEach { category ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    category.name.replace("_", " ").lowercase()
                                        .replaceFirstChar { it.uppercaseChar() })
                            },
                            onClick = {
                                selectedCategory = category
                                expandedMain = false
                                selectedSubType = "" // reset subtype when category changes
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // SUBTYPE DROPDOWN
            val subTypes = when (selectedCategory) {
                WorkoutCategory.WEIGHT_TRAINING -> weightTrainingOptions
                WorkoutCategory.CARDIO -> cardioOptions
                WorkoutCategory.CALISTHENICS -> calisthenicsOptions
                else -> emptyList()
            }

            if (selectedCategory != null) {
                ExposedDropdownMenuBox(
                    expanded = expandedSub,
                    onExpandedChange = { expandedSub = !expandedSub }
                ) {
                    TextField(
                        value = selectedSubType.ifEmpty { "Select type" },
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Specific Workout Type") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedSub) },
                        modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expandedSub,
                        onDismissRequest = { expandedSub = false }
                    ) {
                        subTypes.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedSubType = option
                                    expandedSub = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ADD WORKOUT BUTTON
            Button(
                onClick = {
                    if (selectedCategory != null && selectedSubType.isNotEmpty()) {
                        viewModel.addWorkout(
                            category = selectedCategory!!,
                            subType = selectedSubType,
                            date = "2025-01-01",       // replace with real input later
                            description = "Auto-added" // replace with real input later
                        )
                    }
                },
                enabled = selectedCategory != null && selectedSubType.isNotEmpty()
            ) {
                Text("Add Workout")
            }

            Spacer(modifier = Modifier.height(16.dp))

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
}
