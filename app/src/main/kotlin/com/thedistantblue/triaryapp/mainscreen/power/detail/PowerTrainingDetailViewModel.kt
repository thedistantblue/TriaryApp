package com.thedistantblue.triaryapp.mainscreen.power.detail

import androidx.lifecycle.ViewModel
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao
import com.thedistantblue.triaryapp.entities.base.Exercise
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails

class PowerTrainingDetailViewModel(
        private val exerciseDao: ExerciseDao,
        private val exerciseDetailsDao: ExerciseDetailsDao
): ViewModel() {

    fun getExercises(trainingId: String,
                     subscriber: (Collection<ExerciseDetails>) -> Unit
    ) {
        exerciseDetailsDao.findAllByTrainingId(trainingId).subscribe(subscriber)
    }

    fun deleteExercise(exercise: Exercise,
                       subscriber: () -> Unit
    ) {
        exerciseDao.delete(exercise).subscribe(subscriber)
    }
}