package com.example.composedesign.ui.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedesign.ui.ui.theme.ComposeDesignTheme
import com.example.composedesign.ui.util.startActivity
import com.example.composedesign.ui.view.animation.AnimationActivity
import com.example.composedesign.ui.view.shoes.ShoesActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDesignTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainBody()
                }
            }
        }
    }
}

@Composable
fun MainBody() {
    val context = LocalContext.current
    val performClick : () -> Unit = {
        context.startActivity(AnimationActivity::class.java)
    }

    LaunchedEffect(true) {
        performClick()
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(20.dp)
    ) {
        item {
            MainButton(
                text = "Nike UI",
                color = Color(0xFFE53935)
            ) {
                context.startActivity(ShoesActivity::class.java)
            }
        }

        item {
            MainButton(
                text = "Animation",
                color = Color(0xFFD81B60)
            ) {
                context.startActivity(AnimationActivity::class.java)
            }
        }
    }
}

/**
 * 메인화면 -> 다른 화면으로 이동하는 버튼
 * @param text 버튼에 들어갈 문구
 * @param color 버튼 색상
 * @param onClickListener 버튼 클릭 리스너
 * **/
@Composable
fun MainButton(
    text: String,
    color: Color,
    onClickListener: () -> Unit
) {
    Button(
        onClick = onClickListener,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = Color.White
        )
    ) {
        Text(text = text)
    }
}