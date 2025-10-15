package com.example.regiment.data

class WorkoutViewModel : ViewModel() {
    private val workouts = mutableStateListOf<Workout>()
    //behave like MutableList<Workout> notifies when their is a list change
    val workouts: List<Workout>
    // allows you to access with out editing the data encapsulation

     private var currentId = 0
    //simple counter to assign a unique ID to each Workout added

   //function adds a new workout to the list, assigns a unique ID and then increments the counter.
  fun addWorkout(type: String, date: String) {
        workouts.add(Workout(id = currentId++, type = type, date = date))
    }

    //function deletes a workout by ID, 
    //removeIf is a Kotlin/Java function that removes all items matching the condition
    fun deleteWorkout(id: Int) {
        _workouts.removeIf { it.id == id }
    }
