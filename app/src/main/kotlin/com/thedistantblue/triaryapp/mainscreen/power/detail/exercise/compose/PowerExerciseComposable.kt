package com.thedistantblue.triaryapp.mainscreen.power.detail.exercise.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.entities.base.Exercise
import com.thedistantblue.triaryapp.theme.components.NameDescriptionScreen
import java.util.*

@Composable
fun PowerExerciseComposable(trainingId: UUID,
                            navController: NavController,
                            exerciseId: String? = null,
                            viewModel: PowerExerciseViewModel
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.value

    var nameState by remember { mutableStateOf("") }
    var descriptionState by remember { mutableStateOf("") }

    if (exerciseId == null) {
        NameDescriptionScreen(nameHint = R.string.training_detail_exercise_name_hint,
                              descriptionHint = R.string.training_detail_exercise_description_hint,
                              buttonHint = R.string.training_detail_exercise_create_exercise,
                              name = nameState,
                              description = descriptionState,
                              onNameChanged = { nameState = it },
                              onDescriptionChanged = { descriptionState = it }
        ) { name, description ->
            val exercise = Exercise(trainingId, name, description)
            viewModel.createExercise(exercise)
            returnAndShowToast(context, navController, R.string.training_detail_exercise_created_toast)
        }
    } else {
        viewModel.getExercise(exerciseId)
        NameDescriptionScreen(nameHint = R.string.training_detail_exercise_name_hint,
                              descriptionHint = R.string.training_detail_exercise_description_hint,
                              buttonHint = R.string.training_detail_exercise_update_exercise,
                              name = uiState.name,
                              description = uiState.description,
                              onNameChanged = { viewModel.updateExerciseName(it) },
                              onDescriptionChanged = { viewModel.updateExerciseDescription(it) }
        ) { name, description ->
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