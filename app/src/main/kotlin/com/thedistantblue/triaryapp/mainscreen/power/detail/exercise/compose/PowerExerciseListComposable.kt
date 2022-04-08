package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose

import android.content.res.Resources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.*
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.entities.base.Exercise

@Composable
fun PowerExerciseListScreen(
        trainingId: String,
        exerciseDao: ExerciseDetailsDao,
        lifecycleOwner: LifecycleOwner,
        navigateToCreate: () -> Unit,
        navigateToUpdate: (Exercise) -> Unit,
) {
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
        backgroundColor = MaterialTheme.colors.background
    )
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

    Card(
        shape = RoundedCornerShape(4.dp),
        onClick = onClickNavigateToUpdate,
        backgroundColor = Color.DarkGray,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = exerciseDetails.exercise.name)
        }
    }
}