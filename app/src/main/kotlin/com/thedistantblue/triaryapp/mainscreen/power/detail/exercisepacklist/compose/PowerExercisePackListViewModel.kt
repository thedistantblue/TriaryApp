package com.thedistantblue.triaryapp.mainscreen.power.detail.exercisepacklist.compose

import androidx.lifecycle.ViewModel
import com.thedistantblue.triaryapp.database.room.dao.PackDao
import com.thedistantblue.triaryapp.database.room.dao.details.PackDetailsDao
import com.thedistantblue.triaryapp.entities.base.Pack
import com.thedistantblue.triaryapp.entities.composite.details.PackDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PowerExercisePackListViewModel(
        private val packDao: PackDao,
        private val packDetailsDao: PackDetailsDao
): ViewModel() {
    private val uiStateFlow = MutableStateFlow(listOf<PackDetails>())
    val uiState: StateFlow<List<PackDetails>> = uiStateFlow.asStateFlow()

    fun getPacks(trainingId: String) {
        packDetailsDao.findAllByTrainingId(trainingId).subscribe { exercises ->
            uiStateFlow.value = exercises
        }
    }

    fun deletePack(pack: Pack) {
        packDao.delete(pack).subscribe {
            getPacks(pack.trainingId.toString())
        }
    }
}