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
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource

import androidx.lifecycle.*
import androidx.navigation.NavController
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.theme.components.TriaryAppSwipeToDismissCard
import kotlinx.coroutines.DelicateCoroutinesApi

@Composable
fun PowerExerciseListComposable(navController: NavController,
                                trainingId: String,
                                viewModel: PowerExerciseListViewModel
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
                 viewModel: PowerExerciseListViewModel
) {
    val exercisesRemember = remember { mutableStateListOf<ExerciseDetails>() }
    val uiState by viewModel.uiState.collectAsState()

    viewModel.getExercises(trainingId)

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
               verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(uiState.size) { item ->
            ExerciseListItem(exercisesRemember, uiState.get(item), navController, viewModel)
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
                             viewModel: PowerExerciseListViewModel
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