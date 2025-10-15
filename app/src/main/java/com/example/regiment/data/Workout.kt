package com.example.regiment.data
import java.time.localDate // not sure yet

enum class WorkoutType {
    WEIGHT_TRAINING,
    CARDIO,
    CALISTHENICS
}

    val id: String = java.util.UUID.randomUUID().toString(), // name of workout
    val date: LocalDate, // When the workout is/was done
    val type: WorkoutType, // workout category
    val exercises: List<Exercise> = emptyList(), // List of exercises
    val isPlanned: Boolean = false // planned head of a workout
