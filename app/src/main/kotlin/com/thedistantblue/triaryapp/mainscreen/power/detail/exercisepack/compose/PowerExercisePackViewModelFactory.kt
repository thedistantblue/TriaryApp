package com.thedistantblue.triaryapp.mainscreen.power.detail.exercisepack.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.thedistantblue.triaryapp.database.room.dao.PackDao

class PowerExercisePackViewModelFactory {
    companion object {
        fun getFactory(packDao: PackDao): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                    return PowerExercisePackViewModel(packDao) as T
                }
            }
        }
    }
}