package com.example.composedesign.ui.view.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TransitionAnimation() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            var state by remember { mutableStateOf(false) }

            Button(onClick = { state = state.not() }) {
                Text(text = "Fade Click")
            }

            AnimatedVisibility(
                visible = state,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TransitionBox("FadeIn() + FadeOut()", Color(0xFFE53935))
            }
        }

        item {
            var state by remember { mutableStateOf(false) }

            Button(onClick = { state = state.not() }) {
                Text(text = "Slide Click")
            }

            AnimatedVisibility(
                visible = state,
                enter = slideIn(
                    tween(200, easing = LinearEasing)
                ) {
                    IntOffset(-it.width / 2, -it.height / 2)
                },
                exit = slideOut(
                    tween(200, easing = LinearEasing)
                ) {
                    IntOffset(it.width / 2, it.height / 2)
                }
            ) {
                TransitionBox("slideIn() + slideOut()", Color(0xFFD81B60))
            }
        }

        item {
            var state by remember { mutableStateOf(false) }

            Button(onClick = { state = state.not() }) {
                Text(text = "Slide Horizontal Click")
            }

            AnimatedVisibility(
                visible = state,
                enter = slideInHorizontally (
                    tween(200, easing = LinearEasing)
                ) {
                    it / 2
                },
                exit = slideOutHorizontally (
                    tween(200, easing = LinearEasing)
                ) {
                    it / 2
                }
            ) {
                TransitionBox("slideInHorizontally() + slideOutHorizontally()", Color(0xFF8E24AA))
            }
        }

        item {
            var state by remember { mutableStateOf(false) }

            Button(onClick = { state = state.not() }) {
                Text(text = "Slide Vertical Click")
            }

            AnimatedVisibility(
                visible = state,
                enter = slideInVertically (
                    tween(200, easing = LinearEasing)
                ) {
                    it / 2
                },
                exit = slideOutVertically (
                    tween(200, easing = LinearEasing)
                ) {
                    it / 2
                }
            ) {
                TransitionBox("slideInVertically() + slideOutVertically()", Color(0xFF3949AB))
            }
        }

        item {
            var state by remember { mutableStateOf(false) }

            Button(onClick = { state = state.not() }) {
                Text(text = "Scale Click")
            }

            AnimatedVisibility(
                visible = state,
                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)),
                exit = scaleOut()
            ) {
                TransitionBox("scaleIn() + scaleOut", Color(0xFF1E88E5))
            }
        }

        item {
            var state by remember { mutableStateOf(false) }

            Button(onClick = { state = state.not() }) {
                Text(text = "Expand Click")
            }

            AnimatedVisibility(
                visible = state,
                enter = expandIn(expandFrom = Alignment.Center) { IntSize(100, 50) },
                exit = shrinkOut(shrinkTowards = Alignment.BottomEnd) { IntSize(it.width / 2, it.height / 2) }
            ) {
                TransitionBox("expandIn() + expandOut", Color(0xFF00897B))
            }
        }

        item {
            var state by remember { mutableStateOf(false) }

            Button(onClick = { state = state.not() }) {
                Text(text = "Expand Click")
            }

            AnimatedVisibility(
                visible = state,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                // Fade in/out the background and the foreground.
                Box(Modifier.fillMaxSize().background(Color.DarkGray)) {
                    Box(
                        Modifier
                            .align(Alignment.Center)
                            .animateEnterExit(
                                // Slide in/out the inner box.
                                enter = slideInVertically(),
                                exit = slideOutVertically()
                            )
                            .sizeIn(minWidth = 256.dp, minHeight = 64.dp)
                            .background(Color.Red)
                    ) {
                        // Content of the notification…
                    }
                }
            }
        }

    }
}

@Composable
fun TransitionBox(
    text: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .background(color)
            .padding(20.dp)
    ) {
        Text(text = text, color = Color.White)
    }
}