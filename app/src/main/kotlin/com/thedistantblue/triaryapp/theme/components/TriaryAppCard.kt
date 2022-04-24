package com.thedistantblue.triaryapp.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TriaryAppCard(
    onClickAction: () -> Unit,
    content: @Composable () -> Unit
) {
    val modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)

    Card(
        shape = RoundedCornerShape(8.dp),
        onClick = onClickAction,
        modifier = modifier,
        content = content
    )
}