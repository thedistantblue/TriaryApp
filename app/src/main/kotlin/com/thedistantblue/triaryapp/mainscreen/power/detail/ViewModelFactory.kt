package com.thedistantblue.triaryapp.mainscreen.power.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao
import com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose.PowerExerciseListViewModel

class ViewModelFactory {
    companion object {
        fun getFactory(exerciseDao: ExerciseDao,
                       exerciseDetailsDao: ExerciseDetailsDao
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                    return PowerExerciseListViewModel(exerciseDao, exerciseDetailsDao) as T
                }
            }
        }
    }
}