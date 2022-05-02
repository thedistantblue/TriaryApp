package com.thedistantblue.triaryapp.theme.components

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TriaryAppCard(
    onClickAction: () -> Unit,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)

    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart) {
                Toast.makeText(context, "asdasd", Toast.LENGTH_LONG).show()
            }
            false
        }
    )

    SwipeToDismiss(
        state = dismissState,
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss

            val color by animateColorAsState(
                targetValue = when (dismissState.targetValue) {
                    DismissValue.Default -> Color.LightGray
                    DismissValue.DismissedToEnd -> Color.Green
                    DismissValue.DismissedToStart -> Color.Red
                }
            )

            val icon = when (direction) {
                DismissDirection.StartToEnd -> Icons.Default.Done
                DismissDirection.EndToStart -> Icons.Default.Delete
            }

            val scale by animateFloatAsState(
                targetValue = if (dismissState.targetValue == DismissValue.Default)
                    0.8f else 1.2f)

            val alignment = when (direction) {
                DismissDirection.EndToStart -> Alignment.CenterEnd
                DismissDirection.StartToEnd -> Alignment.CenterStart
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(start = 12.dp, end = 12.dp),
                contentAlignment = alignment
            ) {
                Icon(icon, contentDescription = "icon", modifier = Modifier.scale(scale))
            }
        },
        dismissThresholds = { FractionalThreshold(0.2f) },
        dismissContent = {
            Card(
                shape = RoundedCornerShape(8.dp),
                onClick = onClickAction,
                modifier = modifier,
                content = content
            )
        }
    )

/*    Card(
        shape = RoundedCornerShape(8.dp),
        onClick = onClickAction,
        modifier = modifier,
        content = content
    )*/
}