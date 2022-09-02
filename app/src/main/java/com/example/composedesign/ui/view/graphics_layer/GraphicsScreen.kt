package com.example.composedesign.ui.view.graphics_layer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composedesign.R
import kotlin.math.abs

@Composable
fun GraphicsScreen() {
    var alpha by remember { mutableStateOf(1f) }
    var translationX by remember { mutableStateOf(0f) }
    var translationY by remember { mutableStateOf(0f) }
    var shadowElevation by remember { mutableStateOf(0f) }
    var scaleX by remember { mutableStateOf(1f) }
    var scaleY by remember { mutableStateOf(1f) }
    var rotationX by remember { mutableStateOf(0f) }
    var rotationY by remember { mutableStateOf(0f) }
    var rotationZ by remember { mutableStateOf(0f) }
    var cameraDistance by remember { mutableStateOf(DefaultCameraDistance) }
    var originX by remember { mutableStateOf(0f) }
    var originY by remember { mutableStateOf(0f) }
    var render by remember { mutableStateOf(1f) }

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFEEB1))
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.card1),
                contentDescription = "card",
                modifier = Modifier
                    .size(width = 200.dp, height = 300.dp)
//                    .background(Color.White)
                    .graphicsLayer(
                        alpha = alpha,
                        translationX = translationX,
                        translationY = translationY,
                        shadowElevation = shadowElevation,
                        ambientShadowColor = Color.Red,
                        spotShadowColor = Color.Blue,
                        scaleX = scaleX,
                        scaleY = scaleY,
                        rotationX = rotationX,
                        rotationY = rotationY,
                        rotationZ = rotationZ,
                        cameraDistance = cameraDistance,
                        transformOrigin = TransformOrigin(originX, originY),
//                        shape = RoundedCornerShape(150.dp),
                        clip = true,
                        renderEffect = BlurEffect(render, render)
                    )
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomSlider(
                value = alpha,
                text = "alpha"
            ) {
                alpha = it
            }

            CustomSlider(
                value = translationX,
                text = "translationX",
                valueRange = -100f..100f
            ) {
                translationX = it
            }

            CustomSlider(
                value = translationY,
                text = "translationY",
                valueRange = -100f..100f
            ) {
                translationY = it
            }

            CustomSlider(
                value = shadowElevation,
                text = "shadowElevation",
                valueRange = 0f..100f
            ) {
                shadowElevation = it
            }

            CustomSlider(
                value = scaleX,
                text = "scaleX",
                valueRange = 0f..2f
            ) {
                scaleX = it
            }

            CustomSlider(
                value = scaleY,
                text = "scaleY",
                valueRange = 0f..2f
            ) {
                scaleY = it
            }

            CustomSlider(
                value = rotationX,
                text = "rotationX",
                valueRange = 0f..360f
            ) {
                rotationX = it
            }

            CustomSlider(
                value = rotationY,
                text = "rotationY",
                valueRange = 0f..360f
            ) {
                rotationY = it
            }

            CustomSlider(
                value = rotationZ,
                text = "rotationZ",
                valueRange = 0f..360f
            ) {
                rotationZ = it
            }

            CustomSlider(
                value = cameraDistance,
                text = "cameraDistance",
                valueRange = 0f..20f
            ) {
                cameraDistance = it
            }

            CustomSlider(
                value = originX,
                text = "originX",
                valueRange = 0f..3f
            ) {
                originX = it
            }

            CustomSlider(
                value = originY,
                text = "originY",
                valueRange = 0f..3f
            ) {
                originY = it
            }

            CustomSlider(
                value = render,
                text = "render",
                valueRange = 1f..30f
            ) {
                render = it
            }
        }
    }
}

@Composable
fun CustomSlider(
    value: Float,
    text: String,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    onValueChange: (Float) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Text(text = "$text $value", color = Color.Black, fontWeight = FontWeight.Bold)
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFFFFC400),
                activeTrackColor = Color(0xFF707070)
            )
        )
    }
}

// https://medium.com/mobile-app-development-publication/have-fun-with-jetpack-compose-graphicslayer-modifier-e39c12a4791f
@Composable
fun DoubleSide(
    translationX: Float = 0f,
    translationY: Float = 0f,
    rotationX: Float = 0f,
    rotationY: Float = 0f,
    rotationZ: Float = 0f,
    cameraDistance: Float = 8f,
    flipType: FLIPTYPE,
    front: @Composable () -> Unit,
    back: @Composable () -> Unit
) {
    fun isHorizontallyFlip() = (abs(rotationX) % 360 > 90f
            && abs(rotationX) % 360 < 270f)

    fun isVerticallyFlip() = (abs(rotationY) % 360 > 90f
            && abs(rotationY) % 360 < 270f)

    fun isFlipped() = isVerticallyFlip() xor isHorizontallyFlip()

    if (isFlipped()) {
        val rotationXBack =
            if (flipType == FLIPTYPE.HORIZONTAL)
                rotationX - 180
            else
                rotationX

        val rotationYBack =
            if (flipType == FLIPTYPE.VERTICAL)
                rotationY - 180
            else
                -rotationY
        Box(
            Modifier
                .graphicsLayer(
                    translationX = translationX,
                    translationY = translationY,
                    rotationX = rotationXBack,
                    rotationY = rotationYBack,
                    rotationZ = -rotationZ,
                    cameraDistance = cameraDistance
                )
        ) {
            back()
        }
    } else {
        Box(
            Modifier
                .graphicsLayer(
                    translationX = translationX,
                    translationY = translationY,
                    rotationX = rotationX,
                    rotationY = rotationY,
                    rotationZ = rotationZ,
                    cameraDistance = cameraDistance
                )
        ) {
            front()
        }
    }
}

enum class FLIPTYPE {
    HORIZONTAL,
    VERTICAL
}