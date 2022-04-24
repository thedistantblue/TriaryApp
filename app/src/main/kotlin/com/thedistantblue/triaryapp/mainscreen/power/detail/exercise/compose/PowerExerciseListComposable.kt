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
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.entities.base.Exercise
import com.thedistantblue.triaryapp.theme.TriaryAppTheme
import com.thedistantblue.triaryapp.theme.components.TriaryAppCard

@Composable
fun PowerExerciseListScreen(
        trainingId: String,
        exerciseDao: ExerciseDetailsDao,
        lifecycleOwner: LifecycleOwner,
        navigateToCreate: () -> Unit,
        navigateToUpdate: (Exercise) -> Unit,
) {
    TriaryAppTheme {
        Scaffold(
            content = {
                ExerciseList(trainingId, exerciseDao, lifecycleOwner, navigateToUpdate)
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text(stringResource(R.string.training_detail_exercise_add_button)) },
                    onClick = navigateToCreate
                )
            },
        )
    }
}

@Composable
private fun ExerciseList(trainingId: String,
                         exerciseDao: ExerciseDetailsDao,
                         lifecycleOwner: LifecycleOwner,
                         navigateToUpdate: (Exercise) -> Unit
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

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp,
                                              vertical = 8.dp),
               verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(exercisesRemember) { item ->
            ExerciseListItem(item, navigateToUpdate)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ExerciseListItem(exerciseDetails: ExerciseDetails, navigateToUpdate: (Exercise) -> Unit) {
    val onClickNavigateToUpdate: () -> Unit = {
        navigateToUpdate.invoke(exerciseDetails.exercise)
    }

    TriaryAppCard(
        onClickAction = onClickNavigateToUpdate,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = exerciseDetails.exercise.name)
        }
    }
}