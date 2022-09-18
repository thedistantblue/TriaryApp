package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.dp
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao
import com.thedistantblue.triaryapp.mainscreen.power.detail.PowerTrainingDetailViewModel
import com.thedistantblue.triaryapp.theme.components.TriaryAppSwipeToDismissCard
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun PowerExerciseListComposable(navController: NavController,
                                trainingId: String,
                                viewModel: PowerTrainingDetailViewModel
) {
    Scaffold(
        content = {
            ExerciseList(navController, trainingId, viewModel)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(stringResource(R.string.training_detail_exercise_add_button)) },
                onClick = { navController.navigate("power_exercise/create") }
            )
        },
    )
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ExerciseList(navController: NavController,
                 trainingId: String,
                 viewModel: PowerTrainingDetailViewModel
) {
    val exercisesRemember = remember { mutableStateListOf<ExerciseDetails>() }

    viewModel.getExercises(trainingId) {
        exercisesRemember.clear()
        exercisesRemember.addAll(it)
    }

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
               verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(exercisesRemember) { item ->
            ExerciseListItem(exercisesRemember, item, navController, viewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterialApi::class,
       DelicateCoroutinesApi::class
)
@Composable
private fun ExerciseListItem(exercises: SnapshotStateList<ExerciseDetails>,
                             exerciseDetails: ExerciseDetails,
                             navController: NavController,
                             viewModel: PowerTrainingDetailViewModel
) {
    val exerciseId = exerciseDetails.exercise.exerciseId.toString()
    TriaryAppSwipeToDismissCard(
            onClickAction = { navController.navigate("power_exercise/$exerciseId") },
            onDismissedToEndAction = {},
            onDismissedToStartAction = {
                viewModel.deleteExercise(exerciseDetails.exercise) {
                    exercises.remove(exerciseDetails)
                }
            }
    ) {
        Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = exerciseDetails.exercise.name)
        }
    }
}