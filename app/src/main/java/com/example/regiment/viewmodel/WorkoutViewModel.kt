package com.example.regiment.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.regiment.data.Workout
import com.example.regiment.data.WorkoutCategory
import androidx.compose.runtime.*

class WorkoutViewModel : ViewModel() {
    private val workoutss = mutableStateListOf<Workout>()
    //behave like MutableList<Workout> notifies when their is a list change
    val workouts: List<Workout> get() = workoutss

    init {
        // SAMPLE DATA FOR DEVELOPMENT ONLY
        addWorkout(
            category = WorkoutCategory.CARDIO,
            subType = "Running",
            date = "2025-10-19",
            description = "Ran a 5k"
        )
        addWorkout(
            category = WorkoutCategory.WEIGHT_TRAINING,
            subType = "Bench Press",
            date = "2025-10-20",
            description = "Bench Press 3x10"
        )
        addWorkout(
            category = WorkoutCategory.CALISTHENICS,
            subType = "Planks",
            date = "2025-10-21",
            description = "Core workout"
        )
    }

    // allows you to access with out editing the data encapsulation


    //simple counter to assign a unique ID to each Workout added
    /*
    // FILTER STATE
    var selectedCategoryFilter: WorkoutCategory? by mutableStateOf(null)
    var selectedSubTypeFilter: String by mutableStateOf("")

    //function adds a new workout to the list, assigns a unique ID and then increments the counter.
     Update:
     The main category of the workout (e.g., WEIGHT_TRAINING, CARDIO, CALISTHENICS)
     The specific type of workout within the category
     */

fun addWorkout(category: WorkoutCategory, subType: String, date: String, description: String) {
  val formattedType = "${category.name.replace('_', ' ').lowercase().replaceFirstChar { it.uppercase() }} - $subType"
  val newWorkout = Workout(
       id = ( workoutss.maxOfOrNull { it.id } ?: 0) + 1,
       type = formattedType,
       date = date,
       description = description
   )
   workoutss.add(newWorkout)
}

//function deletes a workout by ID,
//removeIf is a Kotlin/Java function that removes all items matching the condition
fun deleteWorkout(id: Int) {
    workoutss.removeIf { it.id == id }
}

/* COMPUTE: workouts visible on screen
val filteredWorkouts: List<Workout>
    get() {
        return workoutss.filter { workout ->
            val matchesCategory = selectedCategoryFilter?.let { category ->
                workout.type.startsWith(
                    category.name.replace("_", " ").lowercase().replaceFirstChar { it.uppercase() }
                )
            } ?: true

            val matchesSubType = if (selectedSubTypeFilter.isNotEmpty()) {
                workout.type.contains(selectedSubTypeFilter, ignoreCase = true)
            } else true

            matchesCategory && matchesSubType
        }
    }

fun clearFilters() {
    selectedCategoryFilter = null
    selectedSubTypeFilter = ""
}
*/


}

