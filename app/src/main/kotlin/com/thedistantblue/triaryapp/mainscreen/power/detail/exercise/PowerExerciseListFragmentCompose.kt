package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
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
import com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose.PowerExerciseListComposable
import com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose.PowerExerciseScreen
import com.thedistantblue.triaryapp.theme.TriaryAppTheme
import java.util.*

class PowerExerciseListFragmentCompose: Fragment() {

    private lateinit var composeView: View
    private lateinit var lifecycleOwner: LifecycleOwner
    private lateinit var trainingDetails: TrainingDetails
    private lateinit var exerciseDao: ExerciseDao
    private lateinit var exerciseDetailsDao: ExerciseDetailsDao
    private lateinit var trainingId: UUID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = RoomDataBaseProvider.getDatabaseWithProxy(requireActivity())
        exerciseDao = database.exerciseDao()
        exerciseDetailsDao = database.exerciseDetailsDao()
        trainingDetails = PowerExerciseListFragmentComposeArgs.fromBundle(requireArguments()).trainingDetails
        trainingId = trainingDetails.training.trainingId
        lifecycleOwner = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireActivity()).apply {
            setContent {
                val navController = rememberNavController()
                TriaryAppTheme {
                    NavHost(navController = navController, startDestination = "power_exercise_list_route") {
                        powerExerciseListRoute(navController = navController)
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        composeView = view
    }

    private fun NavGraphBuilder.powerExerciseListRoute(navController: NavController) {
        navigation(startDestination = "power_exercise_list", route = "power_exercise_list_route") {
            composable("power_exercise_list") {
                PowerExerciseListComposable(navController, trainingId.toString(), exerciseDetailsDao, lifecycleOwner)
            }
            composable("power_exercise/{exerciseId}") {
                PowerExerciseScreen(exerciseDao, ::saveFunction, trainingId, it.arguments?.getString("exerciseId"))
            }
            composable("power_exercise/create") {
                PowerExerciseScreen(exerciseDao, ::createFunction, trainingId, null)
            }
        }
    }

    private fun createFunction(exercise: Exercise) {
        exerciseDao.create(exercise).subscribe {
            val createToastText = R.string.training_detail_exercise_created_toast
            Toast.makeText(requireActivity(), createToastText , Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveFunction(exercise: Exercise) {
        exerciseDao.save(exercise).subscribe {
            val updateToastText = R.string.training_detail_exercise_updated_toast
            Toast.makeText(requireActivity(), updateToastText , Toast.LENGTH_SHORT).show()
        }
    }
}