package com.thedistantblue.triaryapp.mainscreen.power.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao
import com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose.PowerExerciseViewModel

class ExerciseViewModelFactory {
    companion object {
        fun getFactory(exerciseDao: ExerciseDao): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                    return PowerExerciseViewModel(exerciseDao) as T
                }
            }
        }
    }
}