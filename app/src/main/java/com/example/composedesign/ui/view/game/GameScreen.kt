package com.example.composedesign.ui.view.game

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composedesign.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun GameScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getGameBlack())
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            /** 베너 영역 **/
            item { GameHeader() }
            /** 메인 컨텐츠 **/
            item { GameBody() }
        }
        /** 하단바 **/
        GameFooter(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GameHeader() {
    val state = rememberPagerState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(370.dp)
    ) {
        /** 베너 이미지 HorizontalPager **/
        HorizontalPager(
            count = 3,
            state = state
        ) { index ->
            BannerItem(index)
        }

        /** Indicator **/
        HorizontalPagerIndicator(
            pagerState = state,
            activeColor = Color.White,
            inactiveColor = getGameGray(),
            modifier = Modifier
                .padding(bottom = 60.dp, end = 17.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun BannerItem(index: Int) {
    val bannerList = listOf(
        R.drawable.img_game_banner1,
        R.drawable.img_game_banner2,
        R.drawable.img_game_banner3
    )
    val titleList = listOf("SUPER MARIO", "KIRBY", "DIGIMON SURVIVE")

    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = bannerList[index]),
            contentDescription = "banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(370.dp)
        )
        /** 베너 하단 어둡게 처리 **/
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, getGameBlack())
                    )
                )
                .align(Alignment.BottomCenter)
        )
        /** 타이틀 **/
        Text(
            text = titleList[index],
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(start = 10.dp, bottom = 20.dp)
                .align(Alignment.BottomStart)
        )
        /** 게임 정보 **/
        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(start = 10.dp, bottom = 2.dp)
        ) {
            Text(text = "10+", fontSize = 14.sp, color = getGameGray())
            Text(text = "2022", fontSize = 14.sp, color = getGameGray())
            Text(text = "Children,Puzzle", fontSize = 14.sp, color = getGameGray())
        }
    }
}

@Composable
fun GameBody() {
    val state = remember { mutableStateOf("RECOMMEND") }

    Box(modifier = Modifier.padding(top = 18.dp)) {
        Box(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .fillMaxWidth()
                .height(36.dp)
                .border(1.dp, getGameGray(), RoundedCornerShape(6.dp))
        )
        /** Recommend, Ranking Tab **/
        Row(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .height(36.dp)
                .fillMaxWidth()
        ) {
            GameTabItem(
                text = "RECOMMEND",
                isSelected = state.value == "RECOMMEND",
                modifier = Modifier.weight(1f)
            ) {
                state.value = "RECOMMEND"
            }
            GameTabItem(
                text = "RANKING",
                isSelected = state.value == "RANKING",
                modifier = Modifier.weight(1f)
            ) {
                state.value = "RANKING"
            }
        }

        /** 텝에 따른 Content 설정 **/
        Crossfade(targetState = state.value, animationSpec = tween(durationMillis = 500, easing = LinearEasing)) {
            if (it == "RECOMMEND") {
                GameRecommend()
            } else {
                GameRanking()
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun GameTabItem(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClickListener: () -> Unit
) {
    Box(
        modifier = modifier
            .height(36.dp)
            .clickable {
                onClickListener()
            }
            .border(
                border = if (isSelected) BorderStroke(1.dp, Color.White)
                else BorderStroke(0.dp, Color.Transparent),
                shape = RoundedCornerShape(6.dp)
            )
            .background(Color.Transparent)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
        if (isSelected) {
            Text(
                text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = TextStyle(
                    brush = Brush.horizontalGradient(listOf(getGameBlue(), getGamePurple()))
                ),
                modifier = Modifier.align(Alignment.Center)
//                    .graphicsLayer(alpha = 0.99f)
//                    .drawWithCache {
//                        val brush = Brush.horizontalGradient(listOf(getGameBlue(), getGamePurple()))
//                        onDrawWithContent {
//                            drawContent()
//                            drawRect(brush, blendMode = BlendMode.SrcAtop)
//                        }
//                    }
            )
        }
    }
}

@Composable
fun GameRecommend() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp)
    ) {
        val imageList = listOf(
            R.drawable.img_game_icon1,
            R.drawable.img_game_icon2,
            R.drawable.img_game_icon3,
            R.drawable.img_game_icon1,
            R.drawable.img_game_icon2,
            R.drawable.img_game_icon3
        )
        val titleList = listOf(
            "Mario",
            "Kirby",
            "Digimon",
            "Mario",
            "Kirby",
            "Digimon"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
        ) {
            Text(
                text = "HOT GAMES",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Text(text = "more >", fontSize = 14.sp, color = getGameGray())
        }

        Spacer(modifier = Modifier.height(15.dp))

        LazyRow(
            contentPadding = PaddingValues(start = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(imageList) { index, item ->
                HotGamesItem(imageRes = item, title = titleList[index])
            }
        }
    }
}

@Composable
fun HotGamesItem(
    @DrawableRes imageRes: Int,
    title: String,
) {
    Column {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "hot game",
            modifier = Modifier.clip(
                RoundedCornerShape(20.dp)
            )
        )

        Text(
            text = title,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 20.dp)
                .width(90.dp)
        )

        Button(
            onClick = {},
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF084DE7)
            )
        ) {
            Text(text = "Download", fontSize = 14.sp, color = Color.White)
        }
    }
}

@Composable
fun GameRanking() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(top = 70.dp, start = 16.dp, end = 16.dp)
    ) {
        RankingItem(
            rank = "Silver",
            iconSize = 50.dp,
            iconRes = R.drawable.img_game_icon1,
            cardHeight = 110.dp,
            cardColor = Color(0xFFFF6262),
            modifier = Modifier.weight(1f)
        )

        RankingItem(
            rank = "Gold",
            iconSize = 60.dp,
            iconRes = R.drawable.img_game_icon2,
            cardHeight = 134.dp,
            cardColor = Color(0xFFFF6262),
            modifier = Modifier.weight(1f)
        )

        RankingItem(
            rank = "Bronze",
            iconSize = 50.dp,
            iconRes = R.drawable.img_game_icon3,
            cardHeight = 96.dp,
            cardColor = Color(0xFFFF6262),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun RankingItem(
    rank: String,
    iconSize: Dp,
    @DrawableRes iconRes: Int,
    cardHeight: Dp,
    cardColor: Color,
    modifier: Modifier = Modifier
) {
    val rankColor = when (rank) {
        "Gold" -> getGameGold()
        "Silver" -> getGameSilver()
        else -> getGameBronze()
    }

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(top = 20.dp + iconSize / 2)
                .height(cardHeight)
                .fillMaxWidth()
                .clip(RoundedCornerShape(7.dp))
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(cardColor, Color.White),
                        radius = 600f
                    )
                )
        ) {

        }
        Image(
            painter = painterResource(id = R.drawable.ic_crown),
            contentDescription = "crown",
            colorFilter = ColorFilter.tint(rankColor),
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = "icon",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 20.dp)
                .size(iconSize)
        )
    }
}

@Composable
fun GameFooter(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(getGameLightBlack())
    ) {
        Box(
            modifier = Modifier
                .size(82.dp, 57.dp)
                .background(getGameDarkGray())
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "search",
                modifier = Modifier
                    .padding(start = 25.dp)
                    .size(34.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 15.dp)
                .clip(CircleShape)
                .background(getGameBlue())
                .size(30.dp)
                .align(Alignment.CenterStart)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(70.dp),
            modifier = Modifier.align(Alignment.Center)
        ) {
            ConstraintLayout {
                val (text, box) = createRefs()
                Text(
                    text = "HOME",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.constrainAs(text) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                )
                Box(
                    modifier = Modifier
                        .constrainAs(box) {
                            start.linkTo(text.start)
                            end.linkTo(text.end)
                            top.linkTo(text.bottom, 4.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.value(3.dp)
                        }
                        .background(Color.White, RoundedCornerShape(3.dp))
                )
            }
            Text(
                text = "FIND",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = getGameGray()
            )
        }

        Image(
            painter = painterResource(id = R.drawable.img_game_footer),
            contentDescription = "footer",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 37.dp)
                .height(57.dp)
        )

    }
}
