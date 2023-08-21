package com.thedistantblue.triaryapp.mainscreen.power.detail.exerciselist.compose

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao
import com.thedistantblue.triaryapp.database.room.dao.details.ExerciseDetailsDao
import com.thedistantblue.triaryapp.entities.base.Exercise
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails

class PowerExerciseListViewModel(
        private val exerciseDao: ExerciseDao,
        private val exerciseDetailsDao: ExerciseDetailsDao
): ViewModel() {
    val uiState: MutableState<List<ExerciseDetails>> = mutableStateOf(listOf())

    @SuppressLint("CheckResult")
    fun getExercises(trainingId: String) {
        exerciseDetailsDao.findAllByTrainingId(trainingId).subscribe { exercises ->
            uiState.value = exercises
        }
    }

    @SuppressLint("CheckResult")
    fun deleteExercise(exercise: Exercise) {
        exerciseDao.delete(exercise).subscribe {
            getExercises(exercise.trainingId.toString())
        }
    }
}