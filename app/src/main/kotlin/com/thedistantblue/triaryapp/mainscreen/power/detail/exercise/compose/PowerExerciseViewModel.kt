package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao
import com.thedistantblue.triaryapp.entities.base.Exercise

class PowerExerciseViewModel(
        private val exerciseDao: ExerciseDao
): ViewModel() {
    val uiState: MutableState<Exercise> = mutableStateOf(Exercise())
    private var currentExercise = uiState.value

    fun updateExerciseName(name: String) {
        currentExercise = Exercise(currentExercise.exerciseId,
                                   currentExercise.trainingId,
                                   name,
                                   currentExercise.description)
        uiState.value = currentExercise
    }

    fun updateExerciseDescription(description: String) {
        currentExercise = Exercise(currentExercise.exerciseId,
                                   currentExercise.trainingId,
                                   currentExercise.name,
                                   description)
        uiState.value = currentExercise
    }

    fun getExercise(exerciseId: String) {
        currentExercise.exerciseId?.let {
            if (it.toString() != exerciseId) {
                exerciseDao.findById(exerciseId).subscribe { exercise ->
                    currentExercise = exercise
                    uiState.value = currentExercise
                }
            }
        } ?: run {
            exerciseDao.findById(exerciseId).subscribe { exercise ->
                currentExercise = exercise
                uiState.value = currentExercise
            }
        }
    }

    fun createExercise(exercise: Exercise) {
        exerciseDao.create(exercise).subscribe()
    }

    fun saveExercise(exercise: Exercise) {
        exerciseDao.save(exercise).subscribe()
    }

}