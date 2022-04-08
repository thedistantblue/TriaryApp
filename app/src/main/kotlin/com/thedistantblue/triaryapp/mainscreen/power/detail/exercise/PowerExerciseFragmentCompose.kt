package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider
import com.thedistantblue.triaryapp.entities.base.Exercise
import com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose.PowerExerciseScreen

class PowerExerciseFragmentCompose : Fragment() {

    lateinit var trainingId: String
    lateinit var exerciseDao: ExerciseDao
    lateinit var exerciseDetailsDao: ExerciseDetailsDao

    private var exercise: Exercise? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseDao = RoomDataBaseProvider.getDatabaseWithProxy(requireActivity()).exerciseDao()
        exerciseDetailsDao = RoomDataBaseProvider.getDatabaseWithProxy(requireActivity()).exerciseDetailsDao()
        exercise = PowerExerciseFragmentComposeArgs.fromBundle(requireArguments()).exericse
        trainingId = PowerExerciseFragmentComposeArgs.fromBundle(requireArguments()).trainingId
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireActivity()).apply {
            setContent {
                if (exercise == null) {
                    PowerExerciseScreen(::createFunction, trainingId = trainingId)
                } else {
                    PowerExerciseScreen(::saveFunction, exercise = exercise)
                }
            }
        }
    }

    private fun createFunction(exercise: Exercise, callback: () -> Unit) {
        exerciseDao.create(exercise).subscribe {
            callback.invoke()
        }
    }

    private fun saveFunction(exercise: Exercise, callback: () -> Unit) {
        exerciseDao.save(exercise).subscribe {
            callback.invoke()
        }
    }

}