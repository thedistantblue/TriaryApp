package com.thedistantblue.triaryapp.mainscreen.power.detail.exercisepacklist.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thedistantblue.triaryapp.R
import com.thedistantblue.triaryapp.entities.composite.details.PackDetails
import com.thedistantblue.triaryapp.mainscreen.power.detail.PACK_CREATE
import com.thedistantblue.triaryapp.theme.components.TriaryAppSwipeToDismissCard

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun PowerExercisePackListComposable(
        navController: NavController,
        trainingId: String,
        viewModel: PowerExercisePackListViewModel
) {
    Scaffold(
            topBar = {
                TopAppBar(
                        title = {
                            Text(text = stringResource(id = R.string.training_detail_exercise_pack_list),
                                 color = Color.White
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(Icons.Outlined.ArrowBack,
                                     "backIcon")
                            }
                        }
                )
            },
            content = {
                PackList(navController, trainingId, viewModel, it)
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                        text = { Text(stringResource(R.string.training_detail_exercise_pack_add_button)) },
                        onClick = { navController.navigate(PACK_CREATE) }
                )
            },
    )
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun PackList(navController: NavController,
             trainingId: String,
             viewModel: PowerExercisePackListViewModel,
             padding: PaddingValues
) {
    val uiState by viewModel.uiState.collectAsState()

    viewModel.getPacks(trainingId)

    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp,
                                              vertical = 8.dp),
               verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(items = uiState,
              key = {
                  it.pack.packId
              }) {
            PackListItem(it,
                         navController,
                         viewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PackListItem(
        packDetails: PackDetails,
        navController: NavController,
        viewModel: PowerExercisePackListViewModel
) {
    val packId = packDetails.pack.packId.toString()
    TriaryAppSwipeToDismissCard(
            onClickAction = { navController.navigate("power_exercise_pack/$packId") },
            onDismissedToEndAction = {},
            onDismissedToStartAction = {
                viewModel.deletePack(packDetails.pack)
            }
    ) {
        Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = packDetails.pack.name)
        }
    }
}