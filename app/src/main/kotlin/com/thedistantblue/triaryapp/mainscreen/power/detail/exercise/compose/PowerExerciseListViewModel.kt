package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose

import androidx.lifecycle.ViewModel
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao
import com.thedistantblue.triaryapp.entities.base.Exercise
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PowerExerciseListViewModel(
        private val exerciseDao: ExerciseDao,
        private val exerciseDetailsDao: ExerciseDetailsDao
): ViewModel() {

    private val _uiState = MutableStateFlow(listOf<ExerciseDetails>())
    val uiState: StateFlow<List<ExerciseDetails>> = _uiState.asStateFlow()

    fun getExercises(trainingId: String) {
        exerciseDetailsDao.findAllByTrainingId(trainingId).subscribe { exercises ->
            _uiState.value = exercises;
        }
    }

    fun deleteExercise(exercise: Exercise,
                       subscriber: () -> Unit
    ) {
        exerciseDao.delete(exercise).subscribe(subscriber)
    }
}