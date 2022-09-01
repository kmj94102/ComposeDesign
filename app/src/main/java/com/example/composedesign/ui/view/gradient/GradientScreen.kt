package com.example.composedesign.ui.view.gradient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SweepGradient
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp

@Composable
fun GradientScreen() {

    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        LinearGradientTest()
//        HorizontalGradientTest()
//        VerticalGradientTest()
//        RadialGradientTest()
//        SweepGradientTest()
    }

}

@Composable
fun BrushBox(
    brush: Brush,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(brush = brush)
    )
}

@Composable
fun LinearGradientTest() {
    var sliderPosition by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(GradientOffset()) }

    Text(text = sliderPosition.toInt().toString())
    Slider(
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        valueRange = 0f..360f,
        onValueChangeFinished = {
            offset = getGradientOffset(sliderPosition.toInt())
        },
        steps = 3,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.secondary,
            activeTrackColor = MaterialTheme.colors.secondary
        )
    )

    BrushBox(
        brush = Brush.linearGradient(
            colors = getColorList(),
            start = offset.start,
            end = offset.end,
            tileMode = TileMode.Decal
        )
    )

    BrushBox(
        brush = Brush.linearGradient(
            colors = getColorList(),
            start = Offset.Zero,
            end = Offset(100f, 100f),
            tileMode = TileMode.Decal
        )
    )

    BrushBox(
        brush = Brush.linearGradient(
            colors = getColorList(),
            start = Offset.Zero,
            end = Offset(100f, 100f),
            tileMode = TileMode.Clamp
        )
    )

    BrushBox(
        brush = Brush.linearGradient(
            colors = getColorList(),
            start = Offset.Zero,
            end = Offset(100f, 100f),
            tileMode = TileMode.Repeated
        )
    )
    BrushBox(
        brush = Brush.linearGradient(
            colors = getColorList(),
            start = Offset.Zero,
            end = Offset(100f, 100f),
            tileMode = TileMode.Mirror
        )
    )

}

data class GradientOffset(
    val start: Offset = Offset.Zero,
    val end: Offset = Offset(Float.POSITIVE_INFINITY, 0f)
)

fun getGradientOffset(angle: Int): GradientOffset {
    return when (angle) {
        45 -> GradientOffset(
            start = Offset.Zero,
            end = Offset.Infinite
        )
        90 -> GradientOffset(
            start = Offset.Zero,
            end = Offset(0f, Float.POSITIVE_INFINITY)
        )
        135 -> GradientOffset(
            start = Offset(Float.POSITIVE_INFINITY, 0f),
            end = Offset(0f, Float.POSITIVE_INFINITY)
        )
        180 -> GradientOffset(
            start = Offset(Float.POSITIVE_INFINITY, 0f),
            end = Offset.Zero,
        )
        225 -> GradientOffset(
            start = Offset.Infinite,
            end = Offset.Zero
        )
        270 -> GradientOffset(
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset.Zero
        )
        315 -> GradientOffset(
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )
        else -> GradientOffset(
            start = Offset.Zero,
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )
    }
}

@Composable
fun HorizontalGradientTest() {
    BrushBox(
        brush = Brush.horizontalGradient(
            colors = getColorList(),
            startX = 0f,
            endX = 500f,
            tileMode = TileMode.Repeated
        )
    )
}

@Composable
fun VerticalGradientTest() {
    BrushBox(
        brush = Brush.verticalGradient(
            colors = getColorList(),
            startY = 0f,
            endY = 100f,
            tileMode = TileMode.Repeated
        )
    )
}

@Composable
fun RadialGradientTest() {
    BrushBox(
        brush = Brush.radialGradient(
            colors = getColorList(),
            center = Offset.Zero,
            radius = Float.POSITIVE_INFINITY,
        )
    )

    BrushBox(
        brush = Brush.radialGradient(
            colors = getColorList(),
            center = Offset.Infinite,
            radius = Float.POSITIVE_INFINITY,
        )
    )

    BrushBox(
        brush = Brush.radialGradient(
            colors = getColorList(),
            center = Offset(100f, 200f),
            radius = Float.POSITIVE_INFINITY,
        )
    )

    BrushBox(
        brush = Brush.radialGradient(
            colors = getColorList(),
            center = Offset.Unspecified,
            radius = 400f,
        )
    )

//    BrushBox(
//        brush = Brush.radialGradient(
//            colors = getColorList(),
//            center = Offset.Unspecified,
//            radius = Float.POSITIVE_INFINITY,
//            tileMode = TileMode.Decal
//        )
//    )
//
//    BrushBox(
//        brush = Brush.radialGradient(
//            colors = getColorList(),
//            center = Offset.Unspecified,
//            radius = Float.POSITIVE_INFINITY,
//            tileMode = TileMode.Clamp
//        )
//    )
//
//    BrushBox(
//        brush = Brush.radialGradient(
//            colors = getColorList(),
//            center = Offset.Unspecified,
//            radius = Float.POSITIVE_INFINITY,
//            tileMode = TileMode.Repeated
//        )
//    )
//
//    BrushBox(
//        brush = Brush.radialGradient(
//            colors = getColorList(),
//            center = Offset.Unspecified,
//            radius = Float.POSITIVE_INFINITY,
//            tileMode = TileMode.Mirror
//        )
//    )
}

@Composable
fun SweepGradientTest() {
    val colors = listOf(
        Color(0xFFFFE162),
        Color(0xFFFF6464),
        Color(0xFF3D5AFE),
        Color(0xFF00E676)
    )

    BrushBox(
        brush = Brush.sweepGradient(
            colors = colors,
            center = Offset.Unspecified
        )
    )

    BrushBox(
        brush = Brush.sweepGradient(
            colors = colors,
            center = Offset(300f, 100f)
        )
    )

    BrushBox(
        brush = Brush.sweepGradient(
            colors = colors,
            center = Offset(1000f, 300f)
        )
    )
}

private fun getColorList() = listOf(
    Color(0xFFFFE162),
    Color(0xFFFF6464),
    Color(0xFF3D5AFE),
    Color(0xFF00E676),
)

