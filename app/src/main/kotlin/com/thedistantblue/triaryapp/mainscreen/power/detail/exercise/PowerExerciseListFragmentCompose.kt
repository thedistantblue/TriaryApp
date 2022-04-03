package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider
import com.thedistantblue.triaryapp.entities.base.Exercise
import com.thedistantblue.triaryapp.entities.composite.details.TrainingDetails
import com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose.PowerExerciseListScreen

class PowerExerciseListFragmentCompose: Fragment() {

    lateinit var composeView: View
    lateinit var lifecycleOwner: LifecycleOwner
    lateinit var trainingDetails: TrainingDetails
    lateinit var exerciseDetailsDao: ExerciseDetailsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseDetailsDao = RoomDataBaseProvider.getDatabaseWithProxy(requireActivity()).exerciseDetailsDao()
        trainingDetails = PowerExerciseListFragmentComposeArgs.fromBundle(requireArguments()).trainingDetails
        lifecycleOwner = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireActivity()).apply {
            setContent {
                PowerExerciseListScreen(trainingDetails.training.trainingId.toString(),
                                        exerciseDetailsDao,
                                        lifecycleOwner,
                                        ::doNavigateToCreate,
                                        ::doNavigateToUpdate
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        composeView = view
    }

    private fun doNavigateToCreate() {
        val directions =
            PowerExerciseListFragmentComposeDirections
                .actionPowerExerciseListFragmentToPowerExerciseCreationFragment(null, trainingDetails.training.trainingId.toString())
        Navigation.findNavController(composeView).navigate(directions)
    }

    private fun doNavigateToUpdate(exercise: Exercise) {
        val directions =
            PowerExerciseListFragmentComposeDirections
                .actionPowerExerciseListFragmentToPowerExerciseCreationFragment(exercise, trainingDetails.training.trainingId.toString())
        Navigation.findNavController(composeView).navigate(directions)
    }
}