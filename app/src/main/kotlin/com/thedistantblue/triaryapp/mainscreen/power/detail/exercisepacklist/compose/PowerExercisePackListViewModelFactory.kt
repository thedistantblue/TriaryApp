package com.thedistantblue.triaryapp.mainscreen.power.detail.exercisepacklist.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.thedistantblue.triaryapp.database.room.dao.PackDao
import com.thedistantblue.triaryapp.database.room.dao.details.PackDetailsDao

class PowerExercisePackListViewModelFactory {
    companion object {
        fun getFactory(packDao: PackDao,
                       packDetailsDao: PackDetailsDao
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                    return PowerExercisePackListViewModel(packDao, packDetailsDao) as T
                }
            }
        }
    }
}