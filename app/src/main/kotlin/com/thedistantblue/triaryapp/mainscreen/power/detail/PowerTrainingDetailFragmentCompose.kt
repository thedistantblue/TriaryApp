package com.thedistantblue.triaryapp.mainscreen.power.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao
import com.thedistantblue.triaryapp.database.room.database.RoomDataBaseProvider
import com.thedistantblue.triaryapp.entities.base.Exercise
import com.thedistantblue.triaryapp.entities.composite.details.TrainingDetails
import com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose.PowerExerciseComposable
import com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose.PowerExerciseListComposable
import com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose.PowerExerciseListViewModel
import com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose.PowerExerciseViewModel
import com.thedistantblue.triaryapp.theme.TriaryAppTheme
import com.thedistantblue.triaryapp.utils.BundleKeyConstants
import java.util.*

class PowerTrainingDetailFragmentCompose: Fragment() {

    private lateinit var trainingId: UUID
    private lateinit var composeView: View
    private lateinit var exerciseDao: ExerciseDao
    private lateinit var lifecycleOwner: LifecycleOwner
    private lateinit var trainingDetails: TrainingDetails
    private lateinit var exerciseDetailsDao: ExerciseDetailsDao
    private lateinit var exerciseViewModel: Lazy<PowerExerciseViewModel>
    private lateinit var exerciseListViewModel: Lazy<PowerExerciseListViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = RoomDataBaseProvider.getDatabaseWithProxy(requireActivity())
        this.lifecycleOwner = this
        this.exerciseDao = database.exerciseDao()
        this.exerciseDetailsDao = database.exerciseDetailsDao()
        this.exerciseViewModel = viewModels { ExerciseViewModelFactory.getFactory(exerciseDao) }
        this.exerciseListViewModel = viewModels { ExerciseListViewModelFactory.getFactory(exerciseDao, exerciseDetailsDao) }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireActivity()).apply {
            setContent {
                val navController = rememberNavController()
                TriaryAppTheme {
                    NavHost(navController = navController, startDestination = POWER_TRAINING_DETAIL) {
                        composable(POWER_TRAINING_DETAIL) {
                            PowerTrainingDetailComposable(navController)
                        }
                        powerExerciseListRoute(navController)
                        powerExercisePackListRoute(navController)
                        powerExerciseDateListRoute(navController)
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        composeView = view
        trainingDetails = (requireArguments().getSerializable(BundleKeyConstants.TRAINING_KEY) as TrainingDetails?)!!
        trainingId = trainingDetails.training.trainingId
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun NavGraphBuilder.powerExerciseListRoute(navController: NavController) {
        navigation(startDestination = EXERCISE_LIST, route = EXERCISE_LIST_ROUTE) {
            composable(EXERCISE_LIST) {
                PowerExerciseListComposable(navController, trainingId.toString(), exerciseListViewModel.value)
            }
            composable(EXERCISE_UPDATE) {
                PowerExerciseComposable(trainingId,
                                        navController,
                                        it.arguments?.getString("exerciseId"),
                                        exerciseViewModel.value)
            }
            composable(EXERCISE_CREATE) {
                PowerExerciseComposable(trainingId,
                                        navController,
                                        null,
                                        exerciseViewModel.value)
            }
        }
    }

    private fun NavGraphBuilder.powerExercisePackListRoute(navController: NavController) {
        navigation(startDestination = PACK_LIST, route = EXERCISE_PACK_LIST_ROUTE) {
            composable(PACK_LIST) {

            }
            composable(PACK_UPDATE) {

            }
            composable(PACK_CREATE) {

            }
        }
    }

    private fun NavGraphBuilder.powerExerciseDateListRoute(navController: NavController) {
        navigation(startDestination = DATE_LIST, route = EXERCISE_DATE_LIST_ROUTE) {
            composable(DATE_LIST) {

            }
            composable(DATE_UPDATE) {

            }
            composable(DATE_CREATE) {

            }
        }
    }

}