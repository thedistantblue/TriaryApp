package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.entities.base.Exercise
import java.util.*

@Composable
fun PowerExerciseComposable(trainingId: UUID,
                            navController: NavController,
                            exerciseId: String? = null,
                            viewModel: PowerExerciseViewModel
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.value

    if (exerciseId == null) {
        CreateExerciseComposable { name, description ->
            val exercise = Exercise(trainingId, name, description)
            viewModel.createExercise(exercise)
            returnAndShowToast(context, navController, R.string.training_detail_exercise_created_toast)
        }
    } else {
        viewModel.getExercise(exerciseId)
        UpdateExerciseComposable(uiState, viewModel) { name, description ->
            val exercise = Exercise(UUID.fromString(exerciseId), trainingId, name, description)
            viewModel.saveExercise(exercise)
            returnAndShowToast(context, navController, R.string.training_detail_exercise_updated_toast)
        }
    }
}

private fun returnAndShowToast(context: Context, navController: NavController, toastText: Int) {
    navController.popBackStack()
    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
}

@Composable
fun CreateExerciseComposable(onClickExerciseFunction: (String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = name,
                  textStyle = TextStyle.Default.copy(color = Color.White),
                  onValueChange = { name = it },
                  label = { Text(stringResource(R.string.training_detail_exercise_name_hint)) })
        Box(modifier = Modifier.height(5.dp))
        TextField(value = description,
                  textStyle = TextStyle.Default.copy(color = Color.White),
                  onValueChange = { description = it },
                  label = { Text(stringResource(R.string.training_detail_exercise_description_hint)) })
        Box(modifier = Modifier.height(5.dp))
        Button(onClick = {
            onClickExerciseFunction(name, description)
        }) {
            Text(text = stringResource(R.string.training_detail_exercise_create_exercise))
        }
    }
}

@Composable
fun UpdateExerciseComposable(uiState: Exercise,
                             viewModel: PowerExerciseViewModel,
                             onClickExerciseFunction: (String, String) -> Unit
) {
    Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = uiState.name,
                  textStyle = TextStyle.Default.copy(color = Color.White),
                  onValueChange = { viewModel.updateExerciseName(it) },
                  label = { Text(stringResource(R.string.training_detail_exercise_name_hint)) })
        Box(modifier = Modifier.height(5.dp))
        TextField(value = uiState.description,
                  textStyle = TextStyle.Default.copy(color = Color.White),
                  onValueChange = { viewModel.updateExerciseDescription(it) },
                  label = { Text(stringResource(R.string.training_detail_exercise_description_hint)) })
        Box(modifier = Modifier.height(5.dp))
        Button(onClick = {
            onClickExerciseFunction(uiState.name, uiState.description)
        }) {
            Text(text = stringResource(R.string.training_detail_exercise_update_exercise))
        }
    }

}