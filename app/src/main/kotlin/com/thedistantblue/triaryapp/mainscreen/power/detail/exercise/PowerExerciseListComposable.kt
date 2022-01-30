package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise

import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.unit.dp
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDetailsDao
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.thedistantblue.triaryapp.mainscreen.power.detail.PowerTrainingDetailFragmentDirections

@Composable
fun PowerExerciseListScreen(
    trainingId: String,
    exerciseDao: ExerciseDetailsDao,
    lifecycleOwner: LifecycleOwner,
    navFunction: () -> Unit
) {
    Scaffold(
        content = {
            ExerciseList(trainingId, exerciseDao, lifecycleOwner)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Create Power Exercise") },
                onClick = navFunction
            )
        }
    )
}

@Composable
private fun ExerciseList(trainingId: String, exerciseDao: ExerciseDetailsDao, lifecycleOwner: LifecycleOwner) {
    val exercisesSingle = exerciseDao.findAllByTrainingId(trainingId)
    val exercisesState by exercisesSingle.subscribeAsState(initial = mutableListOf())

    val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            exerciseDao.findAllByTrainingId(trainingId).subscribe { exercises ->
                exercisesState.clear()
                exercisesState.addAll(exercises)
            }
        }
    }
    lifecycleOwner.lifecycle.addObserver(observer)

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(exercisesState!!) { item ->
            ExerciseListItem(exerciseDetails = item)
        }
    }
}

@Composable
private fun ExerciseListItem(exerciseDetails: ExerciseDetails) {
    Card(
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "asdasd")
        }
    }
}