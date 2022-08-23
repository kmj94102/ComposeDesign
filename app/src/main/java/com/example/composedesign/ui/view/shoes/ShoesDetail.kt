package com.example.composedesign.ui.view.shoes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
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
fun ShoesDetailScreen(index: Int) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ShoesBlack)
    ) {
        ShoesDetailBody()
        ShoesDetailHeader()
    }

}

@Composable
fun ShoesDetailHeader() {
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
                modifier = Modifier.align(Alignment.Center)
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

@Composable
fun ShoesDetailBody() {
    Box(modifier = Modifier.fillMaxSize()) {
        ShoesImageInfo()
        ShoesDetailInfo(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ShoesImageInfo() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

        val (shoesImage, circle, nickname, indicator) = createRefs()

        Icon(
            painter = painterResource(id = R.drawable.ic_circle),
            contentDescription = "circle",
            tint = ShoesYellow,
            modifier = Modifier
                .size(450.dp)
                .constrainAs(circle) {
                    top.linkTo(parent.top, (-73).dp)
                    end.linkTo(parent.end, (-126).dp)
                }
        )

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
        val imageList = mutableListOf(1, 2, 3)

        HorizontalPager(
            count = imageList.size,
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
                painter = painterResource(id = R.drawable.img_shoes_1),
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
            imageList.forEachIndexed { index, _ ->
                val isCurrentPosition = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .height(6.dp)
                        .width(if (isCurrentPosition) 20.dp else 6.dp)
                        .background(if (isCurrentPosition) Color.White else Color(0xFF66696B))
                )
            }
        }
    }
}

@Composable
fun ShoesDetailInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 37.dp)
    ) {

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

        Spacer(modifier = Modifier.height(35.dp))

        Text(text = "SIZE", style = shoesTextStyle(), fontSize = 20.sp)

        Spacer(modifier = Modifier.height(12.dp))

        SizeRadioButtons(listOf("7", "7.5", "8", "9"))

        Spacer(modifier = Modifier.height(35.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.weight(1f)) {
                Text(text = "COLOR", style = shoesTextStyle(), fontSize = 20.sp)

                Spacer(modifier = Modifier.height(12.dp))

                ColorRadioButtons(listOf(ShoesYellow, Color.Red, Color.Blue))
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

@Composable
fun SizeRadioButtons(
    list: List<String>,
) {
    val selectState = remember { mutableStateOf(0) }

    Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
        list.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (index == selectState.value) ShoesYellow else Color.White)
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
                    }
            )
        }
    }
}