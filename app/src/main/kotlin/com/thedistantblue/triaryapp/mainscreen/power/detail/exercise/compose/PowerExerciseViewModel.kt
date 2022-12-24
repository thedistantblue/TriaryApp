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

    fun getExercise(exerciseId: String) {
        exerciseDao.findById(exerciseId).subscribe { exercise ->
            uiStateFlow.value = exercise
        }
    }

    fun createExercise(exercise: Exercise) {
        exerciseDao.create(exercise).subscribe()
    }

    fun saveExercise(exercise: Exercise) {
        exerciseDao.save(exercise).subscribe()
    }

}