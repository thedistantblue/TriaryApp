package com.thedistantblue.triaryapp.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TriaryAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = TriaryAppColors.DarkColors,
        content = content
    )
}