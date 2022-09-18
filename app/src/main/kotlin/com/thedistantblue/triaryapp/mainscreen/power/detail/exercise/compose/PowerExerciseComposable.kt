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
import androidx.navigation.NavController
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.database.room.dao.ExerciseDao
import com.thedistantblue.triaryapp.entities.base.Exercise
import java.util.*

@Composable
fun PowerExerciseComposable(exerciseDao: ExerciseDao,
                            exerciseFunction: (Exercise, NavController) -> Unit,
                            trainingId: UUID,
                            navController: NavController,
                            exerciseId: String? = null
) {
    val createButtonText = stringResource(R.string.training_detail_exercise_create_exercise)
    val updateButtonText = stringResource(R.string.training_detail_exercise_update_exercise)

    var localExercise = Exercise(UUID.randomUUID(), trainingId)

    var name by remember { mutableStateOf(localExercise.name) }
    var buttonText by remember { mutableStateOf(createButtonText) }
    var description by remember { mutableStateOf(localExercise.description) }

    exerciseId?.let { id ->
        exerciseDao.findById(id).subscribe { exercise ->
            localExercise = exercise
            buttonText = updateButtonText
            name = localExercise.name
            description = localExercise.description
        }
    }

    val onClickExerciseFunction: () -> Unit = {
        localExercise.name = name
        localExercise.description = description
        exerciseFunction.invoke(localExercise, navController)
    }

    Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
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