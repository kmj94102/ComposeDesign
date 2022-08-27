package com.example.composedesign.ui.view.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OtherAnimation() {


//    Box(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        val scale = remember { Animatable(initialValue = 1f) }
//        val color = remember { Animatable(initialValue = Color(0xFFF50057)) }
//        val coroutineScope = rememberCoroutineScope()
//
//        Box(
//            modifier = Modifier
//                .scale(scale.value)
//                .size(40.dp)
//                .align(Alignment.Center)
//                .background(color.value)
//                .clickable {
//                    coroutineScope.launch {
//                        scale.animateTo(
//                            targetValue = 4f,
//                            animationSpec = tween(1000)
//                        )
//                        color.animateTo(
//                            targetValue = Color(0xFFFFC400),
//                            animationSpec = repeatable(
//                                iterations = 3,
//                                animation = tween(durationMillis = 500),
//                                repeatMode = RepeatMode.Reverse
//                            )
//                        )
//                    }
//                }
//        )
//    }

//    Column(modifier = Modifier.fillMaxSize()) {
//        var state by remember { mutableStateOf(0) }
//        Row(Modifier.fillMaxWidth()) {
//            TextButton(text = "plus") {
//                state++
//            }
//            Spacer(modifier = Modifier.width(20.dp))
//            TextButton(text = "minus") {
//                state--
//            }
//        }
//        AnimatedContent(
//            targetState = state,
//            transitionSpec = {
//                if (targetState > initialState) {
//                    enterAnimation() with exitAnimation()
//                } else{
//                    enterAnimation(-1) with exitAnimation(-1)
//                }.using(SizeTransform(clip = false))
//
////                enterAnimation() with exitAnimation() using SizeTransform(clip = false)
//            },
//        ) { count ->
//                Box(
//                    modifier = Modifier
//                        .size(100.dp)
//                        .background(Color(0xFF651FFF))
//                ) {
//                    Text(text = "$count", modifier = Modifier.align(Alignment.Center))
//                }
//        }
//    }

//    Column(modifier = Modifier.fillMaxSize()) {
//        var state by remember { mutableStateOf(false) }
//        val alpha by animateFloatAsState(targetValue = if (state) 1f else 0.5f)
//        val size by animateDpAsState(targetValue = if (state) 100.dp else 50.dp)
//        val color by animateColorAsState(
//            targetValue = if (state) Color(0xFF2979FF) else Color(0xFFFF1744)
//        )
//        val number by animateIntAsState(targetValue = if (state) 100 else 50)
////            animateIntOffsetAsState(targetValue = )
////            animateIntSizeAsState(targetValue = )
////            animateOffsetAsState(targetValue = )
////            animateRectAsState(targetValue = )
////            animateValueAsState(targetValue = , typeConverter = )
//
//        TextButton(text = "click") {
//            state = state.not()
//        }
//
//        Box(
//            modifier = Modifier
//                .size(size)
//                .alpha(alpha)
//                .background(color)
//        ) {
//            Text(text = "$number", modifier = Modifier.align(Alignment.Center))
//        }
//    }

//    Crossfade
//    val state = remember { mutableStateOf(false) }
//    val text = if (state.value) "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum" else "before"
//    TextButton(
//        text = text,
//        modifier = Modifier.animateContentSize()
//    ) {
//        state.value = state.value.not()
//    }

    val state = remember { mutableStateOf("A") }
    TextButton(text = "Click") {
        state.value = if (state.value == "A") "B" else "A"
    }

    Crossfade(targetState = state.value) {
        when (it) {
            "A" -> {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFFFF9100))
                ) {
                    Text(text = "A", modifier = Modifier.align(Alignment.Center))
                }
            }
            "B" -> {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFF00E676))
                ) {
                    Text(text = "B", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }

}

private fun enterAnimation(direction: Int = 1) =
    slideIn(
        tween(200, easing = LinearEasing)
    ) {
        IntOffset(-it.width / 2 * direction, -it.height / 2 * direction)
    } + fadeIn(animationSpec = tween(220, delayMillis = 90))

private fun exitAnimation(direction: Int = 1) =
    slideOut(
        tween(200, easing = LinearEasing)
    ) {
        IntOffset(it.width / 2 * direction, it.height / 2 * direction)
    } + fadeOut(animationSpec = tween(220, delayMillis = 90))
