package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource

import androidx.lifecycle.*
import androidx.navigation.NavController
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.theme.components.TriaryAppCard

@Composable
fun PowerExerciseListComposable(navController: NavController,
                                trainingId: String,
                                exerciseDetailsDao: ExerciseDetailsDao,
                                lifecycleOwner: LifecycleOwner
) {
    Scaffold(
        content = {
            ExerciseList(trainingId, exerciseDetailsDao, lifecycleOwner, navController)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(stringResource(R.string.training_detail_exercise_add_button)) },
                onClick = { navController.navigate("power_exercise/create") }
            )
        },
    )
}

@Composable
fun ExerciseList(trainingId: String,
                 exerciseDao: ExerciseDetailsDao,
                 lifecycleOwner: LifecycleOwner,
                 navController: NavController
) {

    val exercisesRemember = remember { mutableStateListOf<ExerciseDetails>() }

    val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            exerciseDao.findAllByTrainingId(trainingId).subscribe { exercises ->
                exercisesRemember.clear()
                exercisesRemember.addAll(exercises)
            }
        }
    }
    lifecycleOwner.lifecycle.addObserver(observer)

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
               verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(exercisesRemember) { item ->
            ExerciseListItem(item, navController)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ExerciseListItem(exerciseDetails: ExerciseDetails, navController: NavController) {
    val exerciseId = exerciseDetails.exercise.exerciseId.toString()
    TriaryAppCard(
        onClickAction = { navController.navigate("power_exercise/$exerciseId") }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = exerciseDetails.exercise.name)
        }
    }
}