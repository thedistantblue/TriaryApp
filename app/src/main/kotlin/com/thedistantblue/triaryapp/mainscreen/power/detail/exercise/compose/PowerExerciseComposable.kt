package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.entities.base.Exercise

@Composable
fun PowerExerciseScreen(exerciseFunction: (Exercise, () -> Unit) -> Unit,
                        trainingId: String? = "",
                        exercise: Exercise? = null
) {
    Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }

        TextField(value = name, onValueChange = { name = it }, label = { Text(stringResource(R.string.training_detail_exercise_name_hint)) })
        Box(modifier = Modifier.height(5.dp))
        TextField(value = description, onValueChange = { description = it }, label = { Text(stringResource(R.string.training_detail_exercise_description_hint)) })

        val localExercise = exercise ?: Exercise()
        localExercise.name = name
        localExercise.description = description

        val onClickExerciseFunction: () -> Unit = {
            exerciseFunction.invoke(localExercise) {

            }
        }

        Button(onClick = onClickExerciseFunction) {
            Text("Button")
        }
    }
}