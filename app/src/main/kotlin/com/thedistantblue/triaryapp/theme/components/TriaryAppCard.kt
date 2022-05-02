package com.thedistantblue.triaryapp.theme.components

import androidx.compose.foundation.layout.*
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
    Card(
        shape = RoundedCornerShape(8.dp),
        onClick = onClickAction,
        modifier = Modifier.fillMaxWidth().height(100.dp),
        content = content
    )
}