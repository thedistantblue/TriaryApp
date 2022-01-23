package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thedistantblue.triaryapp.entities.composite.details.ExerciseDetails

@Preview
@Composable
fun PowerExerciseListScreen() {
    Scaffold(
        content = {
            ExerciseListWithFab()
        }
    )
}

@Composable
fun ExerciseListWithFab() {
    val exercises = listOf<ExerciseDetails>()
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(exercises) { data ->
            ExerciseListItem(exerciseDetails = data)
        }
    }
    ExtendedFloatingActionButton(
        text = { Text(text  = "Some EFAB TEXT") },
        onClick = { /*TODO*/ }
    )
}

@Composable
fun ExerciseListItem(exerciseDetails: ExerciseDetails) {

}