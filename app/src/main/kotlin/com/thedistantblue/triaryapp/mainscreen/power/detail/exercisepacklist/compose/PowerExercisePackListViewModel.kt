package com.thedistantblue.triaryapp.mainscreen.power.detail.exercisepacklist.compose

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.thedistantblue.triaryapp.database.room.dao.PackDao
import com.thedistantblue.triaryapp.database.room.dao.details.PackDetailsDao
import com.thedistantblue.triaryapp.entities.base.Pack
import com.thedistantblue.triaryapp.entities.composite.details.PackDetails

class PowerExercisePackListViewModel(
        private val packDao: PackDao,
        private val packDetailsDao: PackDetailsDao
): ViewModel() {
    val uiState: MutableState<List<PackDetails>> = mutableStateOf(listOf())

    @SuppressLint("CheckResult")
    fun getPacks(trainingId: String) {
        packDetailsDao.findAllByTrainingId(trainingId).subscribe { exercises ->
            uiState.value = exercises
        }
    }

    @SuppressLint("CheckResult")
    fun deletePack(pack: Pack) {
        packDao.delete(pack).subscribe {
            getPacks(pack.trainingId.toString())
        }
    }
}