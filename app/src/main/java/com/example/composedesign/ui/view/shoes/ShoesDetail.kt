package com.example.composedesign.ui.view.shoes

import android.app.Activity
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composedesign.R
import com.example.composedesign.ui.ui.theme.ShoesBlack
import com.example.composedesign.ui.ui.theme.ShoesYellow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun ShoesDetailScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ShoesBlack)
    ) {
        ShoesDetailBody()
        ShoesDetailHeader()
    }
}

/**
 * 뒤로가기, 하트 아이콘
 * **/
@Composable
fun ShoesDetailHeader() {
    val activity = LocalContext.current as Activity
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp, start = 23.dp, end = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back",
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable(indication = null, interactionSource = MutableInteractionSource()) {
                        activity.finish()
                    }
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = "heart",
            modifier = Modifier
                .size(34.dp)
        )
    }
}

/**
 * 상품 정보
 * **/
@Composable
fun ShoesDetailBody() {
    val color = remember {
        mutableStateOf(Color(0xFF855F55))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ShoesImageInfo(color.value)
        ShoesDetailInfo(color = color, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun ShoesImageInfo(color: Color) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

        val (shoesImage, circle, nickname, indicator) = createRefs()

        AnimatedContent(
            targetState = color,
            transitionSpec = {
                fadeIn() + slideIn(initialOffset = { fullSize ->
                    IntOffset(
                        fullSize.width / 2,
                        -fullSize.height / 2
                    )
                }) with fadeOut()
            },
            modifier = Modifier
                .size(450.dp)
                .constrainAs(circle) {
                    top.linkTo(parent.top, (-73).dp)
                    end.linkTo(parent.end, (-126).dp)
                }
        ) { selectColor ->
            Icon(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = "circle",
                tint = selectColor,
            )
        }

        Text(
            text = "NIKE AIR",
            style = shoesTextStyle(),
            fontSize = 130.sp,
            modifier = Modifier
                .padding(top = 180.dp)
                .constrainAs(nickname) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        val pagerState = rememberPagerState()
        val listSize = getImageList().size

        HorizontalPager(
            count = listSize,
            state = pagerState,
            modifier = Modifier
                .constrainAs(shoesImage) {
                    top.linkTo(parent.top, 80.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(325.dp)
        ) {
            Image(
                painter = painterResource(id = getImage(color)),
                contentDescription = "shoes",
                modifier = Modifier
                    .size(width = 316.dp, height = 325.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .constrainAs(indicator) {
                    top.linkTo(shoesImage.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            (0 until listSize).forEach {
                val isCurrentPosition = pagerState.currentPage == it
                val size by animateDpAsState(targetValue = if (isCurrentPosition) 20.dp else 6.dp)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .height(6.dp)
                        .width(size)
                        .background(if (isCurrentPosition) Color.White else Color(0xFF66696B))
                )
            }
        }
    }
}

@Composable
fun ShoesDetailInfo(color: MutableState<Color>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 37.dp)
    ) {

        /** 상품 정보 **/
        Text(text = "NIKE AIR", style = shoesTextStyle(), fontSize = 20.sp)

        Spacer(modifier = Modifier.height(4.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "AIR JORDAN 1 MID SE GC",
                style = shoesTextStyle(),
                fontSize = 30.sp,
                modifier = Modifier.weight(1f)
            )
            Text(text = "$856", style = shoesTextStyle(), fontSize = 30.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))

        RatingBar(size = 12.dp, space = 6.dp, rating = 4)
        // 상품 정보 End

        Spacer(modifier = Modifier.height(35.dp))

        /** SIZE **/
        Text(text = "SIZE", style = shoesTextStyle(), fontSize = 20.sp)

        Spacer(modifier = Modifier.height(12.dp))

        SizeRadioButtons(listOf("7", "7.5", "8", "9"), color.value)
        // SIZE End

        Spacer(modifier = Modifier.height(35.dp))

        /** Color & Button **/
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.weight(1f)) {
                Text(text = "COLOR", style = shoesTextStyle(), fontSize = 20.sp)

                Spacer(modifier = Modifier.height(12.dp))

                ColorRadioButtons(getImageList(),  color)
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ShoesYellow
                ),
                modifier = Modifier
                    .size(width = 101.dp, height = 68.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    )
            ) {
                Text(
                    text = "BUY",
                    style = shoesTextStyle(),
                    fontSize = 30.sp,
                    color = ShoesBlack
                )
            }
        }

    }
}

@Composable
fun RatingBar(
    size: Dp,
    space: Dp,
    rating: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(space),
        modifier = modifier
    ) {
        for (i in 1..5) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_star_24),
                contentDescription = "star",
                tint = if (i <= rating) ShoesYellow else Color.White,
                modifier = Modifier.size(size)
            )
        }
    }
}

/**
 * Size 선택 라디오 버튼
 * @param list 선택할 버튼에 들어갈 문구
 * @param color 버튼 색상
 * **/
@Composable
fun SizeRadioButtons(
    list: List<String>,
    color: Color,
) {
    val selectState = remember { mutableStateOf(0) }

    Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
        list.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (index == selectState.value) color else Color.White)
            ) {
                Text(
                    text = item,
                    style = shoesTextStyle(),
                    fontSize = 20.sp,
                    color = ShoesBlack,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            selectState.value = index
                        }
                )
            }
        }
    }
}

@Composable
fun ColorRadioButtons(
    list: List<Color>,
    color: MutableState<Color>,
) {
    val selectState = remember { mutableStateOf(0) }

    Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
        list.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .size(23.dp)
                    .clip(CircleShape)
                    .background(if (index == selectState.value) Color.White else ShoesBlack)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(item)
                    .clickable {
                        selectState.value = index
                        color.value = list[index]
                    }
            )
        }
    }
}

fun getImageList() = listOf(
    Color(0xFF855F55),
    Color(0xFFEAEAEA),
    Color(0xFF4D4D4D)
)

fun getImage(color: Color): Int {
    val list = listOf(R.drawable.img_shoes_1, R.drawable.img_shoes_2, R.drawable.img_shoes_3)
    val index = getImageList().indexOf(color)
    return list[index]
}