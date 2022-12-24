package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose

import androidx.lifecycle.ViewModel
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao
import com.thedistantblue.triaryapp.entities.base.Exercise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PowerExerciseViewModel(
        private val exerciseDao: ExerciseDao
): ViewModel() {

    private val uiStateFlow = MutableStateFlow(Exercise())
    val uiState: StateFlow<Exercise> = uiStateFlow.asStateFlow()
    var currentExercise = uiStateFlow.value

    fun updateExerciseName(name: String) {
        currentExercise = Exercise(currentExercise.exerciseId,
                                   currentExercise.trainingId,
                                   name,
                                   currentExercise.description)
        uiStateFlow.value = currentExercise
    }

    fun updateExerciseDescription(description: String) {
        currentExercise = Exercise(currentExercise.exerciseId,
                                   currentExercise.trainingId,
                                   currentExercise.name,
                                   description)
        uiStateFlow.value = currentExercise
    }

    fun getExercise(exerciseId: String) {
        currentExercise.exerciseId?.let {
            if (it.toString() != exerciseId) {
                exerciseDao.findById(exerciseId).subscribe { exercise ->
                    currentExercise = exercise
                    uiStateFlow.value = currentExercise
                }
            }
        } ?: run {
            exerciseDao.findById(exerciseId).subscribe { exercise ->
                currentExercise = exercise
                uiStateFlow.value = currentExercise
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