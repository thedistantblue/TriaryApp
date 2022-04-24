package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.entities.base.Exercise
import com.thedistantblue.triaryapp.theme.TriaryAppTheme
import java.util.*

@Composable
fun PowerExerciseScreen(exerciseFunction: (Exercise, () -> Unit) -> Unit,
                        trainingId: String? = null,
                        exercise: Exercise? = null
) {
    val localExercise = exercise ?: Exercise()
    trainingId?.let {
        localExercise.exerciseId = UUID.randomUUID()
        localExercise.trainingId = UUID.fromString(it)
    }

    var name by remember { mutableStateOf(localExercise.name) }
    var description by remember { mutableStateOf(localExercise.description) }
    val createToastText = stringResource(R.string.training_detail_exercise_created_toast)
    val updateToastText = stringResource(R.string.training_detail_exercise_updated_toast)
    val createButtonText = stringResource(R.string.training_detail_exercise_create_exercise)
    val updateButtonText = stringResource(R.string.training_detail_exercise_update_exercise)
    val buttonText = exercise?.let { updateButtonText } ?: run { createButtonText }

    val context = LocalContext.current
    val onClickExerciseFunction: () -> Unit = {
        localExercise.name = name
        localExercise.description = description
        exerciseFunction.invoke(localExercise) {
            exercise?.let {
                Toast.makeText(context, updateToastText , Toast.LENGTH_SHORT).show()
            } ?: Toast.makeText(context, createToastText, Toast.LENGTH_SHORT).show()
        }
    }

    TriaryAppTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = name, onValueChange = { name = it }, label = { Text(stringResource(R.string.training_detail_exercise_name_hint)) })
            Box(modifier = Modifier.height(5.dp))
            TextField(value = description, onValueChange = { description = it }, label = { Text(stringResource(R.string.training_detail_exercise_description_hint)) })
            Box(modifier = Modifier.height(5.dp))
            Button(onClick = onClickExerciseFunction) { Text(text = buttonText) }
        }
    }
}