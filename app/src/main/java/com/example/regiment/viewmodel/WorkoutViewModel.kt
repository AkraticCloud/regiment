package com.example.regiment.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.regiment.data.Workout

class WorkoutViewModel : ViewModel() {
    private val workoutss = mutableStateListOf<Workout>()
    //behave like MutableList<Workout> notifies when their is a list change
    val workouts: List<Workout> get() = workoutss
    // allows you to access with out editing the data encapsulation


    //simple counter to assign a unique ID to each Workout added

   //function adds a new workout to the list, assigns a unique ID and then increments the counter.
  fun addWorkout(type: String, date: String, description: String) {
       val newWorkout = Workout(
           id = ( workoutss.maxOfOrNull { it.id } ?: 0) + 1,
           type = type,
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
}
