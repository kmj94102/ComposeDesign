package com.example.composedesign.ui.view.animation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun AsStateAnimation() {
    val booleanState = remember { mutableStateOf(false) }
    val floatState = animateFloatAsState(targetValue = if (booleanState.value) 5f else 0.2f)

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            TextButton(
                text = "floatState"
            ) {
                booleanState.value = booleanState.value.not()
            }

            Text(text = "${floatState.value}")

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .graphicsLayer { alpha = floatState.value }
                    .background(Color.Cyan)
            )
        }

        item {
            var message by remember { mutableStateOf("Hello world") }
            TextButton(text = "click") {
                message = if (message == "Hello world") "I Love Kim Ji Hun. He is handsome guy" else "Hello world"
            }
            Box(
                modifier = Modifier
                    .background(Color.Blue)
                    .animateContentSize()
            ) {
                Text(text = message)
            }
        }

        item {
            var currentPage by remember { mutableStateOf("A") }
            TextButton(text = "click") {
                currentPage = if (currentPage == "A") "B" else "A"
            }

            Crossfade(targetState = currentPage) { screen ->
                when (screen) {
                    "A" -> Text("Page A")
                    "B" -> Text("Page B")
                }
            }
        }
    }

}