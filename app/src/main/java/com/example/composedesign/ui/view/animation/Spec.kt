package com.example.composedesign.ui.view.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimationSpecEx() {

    val state = remember { mutableStateOf(false) }

    TextButton(text = "click") {
        state.value = state.value.not()
    }

    Row(horizontalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.fillMaxWidth()) {
        AnimatedVisibility(
            visible = state.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFFF4511E))
            )
        }

        AnimatedVisibility(
            visible = state.value,
            enter = fadeIn(
                animationSpec = snap(delayMillis = 1000)
            ),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFFFFB300))
            )
        }
    }

}