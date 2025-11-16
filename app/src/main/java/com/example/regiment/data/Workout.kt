package com.example.regiment.data
data class Workout(
    val id: Int,
    val date: String, // When the workout is/was done
    val type: String, // workout category
    val description: String
)

data class WorkoutTypeDetail(
    val category: WorkoutCategory,
    val subType: String,
)

enum class WorkoutCategory {
    WEIGHT_TRAINING, CARDIO, CALISTHENICS
}
