package com.thedistantblue.triaryapp.mainscreen.power.detail.exerciselist.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

import androidx.lifecycle.*
import androidx.navigation.NavController
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.theme.components.TriaryAppSwipeToDismissCard

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun PowerExerciseListComposable(navController: NavController,
                                trainingId: String,
                                viewModel: PowerExerciseListViewModel
) {
    Scaffold(
            topBar = {
                TopAppBar(
                        title = {
                            Text(text = stringResource(id = R.string.training_detail_exercise_list),
                                 color = Color.White
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(Icons.Outlined.ArrowBack,
                                     "backIcon")
                            }
                        }
                )
            },
            content = {
                ExerciseList(navController,
                             trainingId,
                             viewModel,
                             it)
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
                 viewModel: PowerExerciseListViewModel,
                 padding: PaddingValues
) {
    val uiState by viewModel.uiState.collectAsState()

    viewModel.getExercises(trainingId)

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
               verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(items = uiState, key = {
            it.exercise.exerciseId
        }) {
            ExerciseListItem(it, navController, viewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ExerciseListItem(
        exerciseDetails: ExerciseDetails,
        navController: NavController,
        viewModel: PowerExerciseListViewModel
) {
    val exerciseId = exerciseDetails.exercise.exerciseId.toString()
    TriaryAppSwipeToDismissCard(
            onClickAction = { navController.navigate("power_exercise/$exerciseId") },
            onDismissedToEndAction = {},
            onDismissedToStartAction = {
                viewModel.deleteExercise(exerciseDetails.exercise)
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