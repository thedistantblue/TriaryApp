package com.thedistantblue.triaryapp.mainscreen.power.detail.date.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class PowerExerciseDateViewModelFactory {
    companion object {
        fun getFactory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                    return PowerExerciseDateViewModel() as T
                }
            }
        }
    }
}