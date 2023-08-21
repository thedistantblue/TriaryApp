package com.thedistantblue.triaryapp.mainscreen.power.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thedistantblue.triaryapp.R

@Composable
fun PowerTrainingDetailComposable(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.training_details),
                     color = Color.White
                )
            })
        }
    ) {
        DetailTypesList(navController = navController, padding = it)
    }
}

@Composable
private fun DetailTypesList(navController: NavController,
                            padding: PaddingValues
) {
    Column(modifier = Modifier.padding(10.dp),
           verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(EXERCISE_LIST_ROUTE) }
        ) {
            Text(text = stringResource(id = R.string.training_detail_exercise_list),
                 color = Color.White
            )
            Icon(painter = painterResource(id = R.drawable.right_arrow),
                 contentDescription = null,
                 tint = Color.White
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(EXERCISE_PACK_LIST_ROUTE) }
        ) {
            Text(text = stringResource(id = R.string.training_detail_exercise_pack_list),
                 color = Color.White
            )
            Icon(painter = painterResource(id = R.drawable.right_arrow),
                 contentDescription = null,
                 tint = Color.White
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(EXERCISE_DATE_LIST_ROUTE) }
        ) {
            Text(text = stringResource(id = R.string.training_detail_dates_list),
                 color = Color.White
            )
            Icon(painter = painterResource(id = R.drawable.right_arrow),
                 contentDescription = null,
                 tint = Color.White
            )
        }
    }
}