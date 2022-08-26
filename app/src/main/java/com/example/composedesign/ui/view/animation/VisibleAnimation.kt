package com.example.composedesign.ui.view.animation

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VisibleAnimation() {
    var visible1 by remember { mutableStateOf(false) }
    var visible2 by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { visible1 = visible1.not() }) {
            Text(text = "Click")
        }

        AnimatedVisibility(visible = visible1) {
            Box(modifier = Modifier.size(100.dp).background(Color.Cyan))
        }

        Button(onClick = { visible2 = visible2.not() }) {
            Text(text = "Click")
        }

        AnimatedVisibility(
            visible = visible2,
            enter = slideInHorizontally() + fadeIn(),
            exit = slideOutHorizontally() + fadeOut()
        ) {
            Box(modifier = Modifier.size(100.dp).background(Color.Cyan))
        }
    }
}