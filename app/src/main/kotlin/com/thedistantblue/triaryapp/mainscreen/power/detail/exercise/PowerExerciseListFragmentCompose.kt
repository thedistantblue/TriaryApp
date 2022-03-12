package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider
import com.thedistantblue.triaryapp.entities.base.Training
import com.thedistantblue.triaryapp.entities.composite.details.TrainingDetails

class PowerExerciseListFragmentCompose: Fragment() {

    lateinit var exerciseDetailsDao: ExerciseDetailsDao
    lateinit var trainingDetails: TrainingDetails
    lateinit var lifecycleOwner: LifecycleOwner
    lateinit var composeView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseDetailsDao = RoomDataBaseProvider.getDatabaseWithProxy(requireActivity()).exerciseDetailsDao()
        trainingDetails = PowerExerciseListFragmentComposeArgs.fromBundle(requireArguments()).trainingDetails
        lifecycleOwner = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.tab_power_nav_host) as NavHostFragment?

        return ComposeView(requireActivity()).apply {
            setContent {
                PowerExerciseListScreen("trainingDetails.training.trainingId",
                                        exerciseDetailsDao,
                                        lifecycleOwner) {
                    doNavigate()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        composeView = view
    }

    private fun doNavigate() {
        val directions = PowerExerciseListFragmentComposeDirections.actionPowerExerciseListFragmentToPowerExerciseCreationFragment()
        Navigation.findNavController(composeView).navigate(directions)
    }
}