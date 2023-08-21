package com.thedistantblue.triaryapp.mainscreen.power.detail.exercisepack.compose

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.thedistantblue.triaryapp.database.room.dao.PackDao
import com.thedistantblue.triaryapp.entities.base.Pack

class PowerExercisePackViewModel(
        private val packDao: PackDao
): ViewModel() {
    val uiState: MutableState<Pack> = mutableStateOf(Pack())
    private var currentPack = uiState.value

    fun updatePackName(name: String) {
        currentPack = Pack(currentPack.packId,
                           currentPack.trainingId,
                           name,
                           currentPack.description)
        uiState.value = currentPack
    }

    fun updatePackDescription(description: String) {
        currentPack = Pack(currentPack.packId,
                           currentPack.trainingId,
                           currentPack.name,
                           description)
        uiState.value = currentPack
    }

    fun getPack(packId: String) {
        currentPack.packId?.let {
            if (it.toString() != packId) {
                packDao.findById(packId).subscribe { pack ->
                    currentPack = pack
                    uiState.value = currentPack
                }
            }
        } ?: run {
            packDao.findById(packId).subscribe { pack ->
                currentPack = pack
                uiState.value = currentPack
            }
        }
    }

    fun createPack(pack: Pack) {
        packDao.create(pack).subscribe()
    }

    fun savePack(pack: Pack) {
        packDao.save(pack).subscribe()
    }
}