package com.thedistantblue.triaryapp.mainscreen.power.detail.exercisepack.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.entities.base.Pack
import com.thedistantblue.triaryapp.theme.components.NameDescriptionScreen
import java.util.UUID

@Composable
fun PowerExercisePackComposable(trainingId: UUID,
                                navController: NavController,
                                packId: String? = null,
                                viewModel: PowerExercisePackViewModel) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.value

    var nameState by remember { mutableStateOf("") }
    var descriptionState by remember { mutableStateOf("") }

    if (packId == null) {
        NameDescriptionScreen(nameHint = R.string.training_detail_pack_name_hint,
                              descriptionHint = R.string.training_detail_pack_description_hint,
                              buttonHint = R.string.training_detail_pack_create_exercise,
                              name = nameState,
                              description = descriptionState,
                              onNameChanged = { nameState = it },
                              onDescriptionChanged = { descriptionState = it }
        ) { name, description ->
            val pack = Pack(trainingId, name, description)
            viewModel.createPack(pack)
            returnAndShowToast(context, navController, R.string.training_detail_pack_created_toast)
        }
    } else {
        viewModel.getPack(packId)
        NameDescriptionScreen(nameHint = R.string.training_detail_pack_name_hint,
                              descriptionHint = R.string.training_detail_pack_description_hint,
                              buttonHint = R.string.training_detail_pack_update_exercise,
                              name = uiState.name,
                              description = uiState.description,
                              onNameChanged = { viewModel.updatePackName(it) },
                              onDescriptionChanged = { viewModel.updatePackDescription(it) }
        ) { name, description ->
            val pack = Pack(UUID.fromString(packId), trainingId, name, description)
            viewModel.savePack(pack)
            returnAndShowToast(context, navController, R.string.training_detail_pack_updated_toast)
        }
    }

}

private fun returnAndShowToast(context: Context, navController: NavController, toastText: Int) {
    navController.popBackStack()
    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
}