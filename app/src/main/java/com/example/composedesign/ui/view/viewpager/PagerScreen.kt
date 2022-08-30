package com.example.composedesign.ui.view.viewpager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerScreen() {

    val colorList = listOf(
        Color(0xFFFF1744),
        Color(0xFF651FFF),
        Color(0xFF2979FF),
        Color(0xFF00E676),
        Color(0xFFFFC400),
        Color(0xFFFF3D00),
    )
    val tabList = listOf(
        "R",
        "P",
        "B",
        "G",
        "Y",
        "O"
    )

    val state = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = state.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(state, tabPositions)
                )
            }
        ) {
            tabList.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = state.currentPage == index,
                    onClick = {
                        scope.launch {
                            state.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
        
        Spacer(modifier = Modifier.height(10.dp))

        HorizontalPager(
            count = colorList.size,
            state = state,
            contentPadding = PaddingValues(horizontal = 100.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.5f)
        ) { page ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        // 크기 조절
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                        // 투명도 조절
                        alpha = lerp(
                            start = 0.2f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxSize()
                    .background(colorList[page])
            ) {
                Text(
                    text = "Horizontal Pager: $page",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        HorizontalPagerIndicator(
            pagerState = state,
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        LaunchedEffect(state.currentPage) {
            delay(3000)
            val index = (state.currentPage + 1) % state.pageCount
            state.animateScrollToPage(index)
        }
    }
}