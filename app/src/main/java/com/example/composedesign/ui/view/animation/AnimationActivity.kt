package com.example.composedesign.ui.view.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedesign.R
import com.example.composedesign.ui.ui.theme.ComposeDesignTheme

class AnimationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDesignTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AnimationTestBody()
                }
            }
        }
    }
}

@Composable
fun AnimationTestBody() {
    val state = remember { mutableStateOf<AnimationState>(AnimationState.List) }
    
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        
        if (state.value != AnimationState.List) {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_arrow_back_24),
                contentDescription = "back",
                Modifier.clickable {
                    state.value = AnimationState.List
                }
            )
        }
        
        when (state.value) {
            AnimationState.List -> {
                AnimationList(state)
            }
            AnimationState.VisibleAnimation -> {
                VisibleAnimation()
            }
            AnimationState.TransitionAnimation -> {
                TransitionAnimation()
            }
            AnimationState.AnimationSpecEx -> {
                AnimationSpecEx()
            }
            AnimationState.OtherAnimation -> {
                OtherAnimation()
            }
        }
    }
}

@Composable
fun AnimationList(state: MutableState<AnimationState>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.padding(20.dp)
    ) {
        ClickableText(text = "Visible Animation") {
            state.value = AnimationState.VisibleAnimation
        }
        ClickableText(text = "Transition Animation") {
            state.value = AnimationState.TransitionAnimation
        }
        ClickableText(text = "AnimationSpec") {
            state.value = AnimationState.AnimationSpecEx
        }
        ClickableText(text = "OtherAnimation") {
            state.value = AnimationState.OtherAnimation
        }
    }
}

@Composable
fun ClickableText(
    text: String,
    onClickListener: () -> Unit
) {
    Text(
        text = text,
        fontSize = 18.sp,
        style = TextStyle(textDecoration = TextDecoration.Underline),
        modifier = Modifier.clickable {
            onClickListener()
        }
    )
}

@Composable
fun TextButton(
    text: String,
    modifier: Modifier = Modifier,
    onClickListener: () -> Unit
) {
    Button(onClick = { onClickListener() }, modifier = modifier) {
        Text(text = text)
    }
}

sealed class AnimationState {
    object List : AnimationState()
    object VisibleAnimation : AnimationState()
    object TransitionAnimation : AnimationState()
    object AnimationSpecEx : AnimationState()
    object OtherAnimation : AnimationState()
}

