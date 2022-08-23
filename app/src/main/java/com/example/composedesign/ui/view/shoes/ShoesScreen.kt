package com.example.composedesign.ui.view.shoes

import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composedesign.R
import com.example.composedesign.ui.ui.theme.ComposeDesignTheme
import com.example.composedesign.ui.ui.theme.ShoesBlack
import com.example.composedesign.ui.ui.theme.ShoesYellow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@Composable
fun ShoesMainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ShoesBlack)
    ) {
        ShoesMainHeader()
        ShoesMainBody(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        ShoesMainFooter()
    }
}

@Composable
fun ShoesMainHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp, start = 16.dp, end = 16.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "logo",
            modifier = Modifier.size(width = 61.dp, height = 34.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "menu",
            modifier = Modifier.size(34.dp)
        )

        Spacer(modifier = Modifier.width(17.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_basket),
            contentDescription = "basket",
            modifier = Modifier.size(34.dp)
        )

    }
}

@Composable
fun ShoesMainBody(modifier: Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(top = 56.dp, bottom = 100.dp),
        modifier = modifier
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(22.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Basketball",
                    fontSize = 28.sp,
                    style = shoesTextStyle(),
                    color = ShoesYellow
                )
                Text(
                    text = "Running",
                    fontSize = 28.sp,
                    style = shoesTextStyle(),
                    color = Color.White
                )
                Text(
                    text = "Training",
                    fontSize = 28.sp,
                    style = shoesTextStyle(),
                    color = Color.White
                )
            }
        }

        item { Spacer(modifier = Modifier.height(48.dp)) }

        item {
            ShoesViewPager()
        }

    }
}

@Composable
fun ShoesMainFooter() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .background(ShoesYellow)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = "home",
            modifier = Modifier
                .padding(start = 33.dp)
                .size(34.dp)
                .align(Alignment.CenterStart)
        )

        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(ShoesBlack)
                .padding(13.dp)
                .clip(CircleShape)
                .background(ShoesYellow)
                .padding(3.dp)
                .clip(CircleShape)
                .background(ShoesBlack)
                .padding(3.dp)
                .clip(CircleShape)
                .background(ShoesYellow)
                .padding(3.dp)
                .clip(CircleShape)
                .background(ShoesBlack)
                .padding(3.dp)
                .clip(CircleShape)
                .background(ShoesYellow)
                .align(Alignment.Center)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_user),
            contentDescription = "user",
            modifier = Modifier
                .padding(end = 33.dp)
                .size(34.dp)
                .align(Alignment.CenterEnd)
        )

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ShoesViewPager() {

    val shoesImageList = listOf(
        R.drawable.img_card_1, R.drawable.img_card_2, R.drawable.img_card_3,
        R.drawable.img_card_4, R.drawable.img_card_5
    )

    HorizontalPager(
        count = shoesImageList.size + 1,
        contentPadding = PaddingValues(start = 27.dp, end = 85.dp)
    ) { page ->

        val context = LocalContext.current

        if (shoesImageList.size > page) {
            ShoesCard(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        // 크기 조절
                        lerp(
                            start = 0.9f,
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
                    },
                imageRes = shoesImageList[page]
            ) {
                context.startActivity(
                    Intent(context, ShoesDetailActivity::class.java).also {
                        it.putExtra(ShoesDetailActivity.Index, page)
                    }
                )
            }
        } else {
            Button(
                onClick = {  }, 
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ShoesYellow,
                    contentColor = ShoesBlack
                ),
                modifier = Modifier
                    .size(width = 218.dp, height = 300.dp)
                    .clip(RoundedCornerShape(33.dp))
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
            ) {
                Text(text = "더 보기", style = shoesTextStyle())
            }
        }
    }
}

@Composable
fun ShoesCard(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
    onClickListener: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(40.dp),
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier
                .size(width = 255.dp, height = 355.dp)
        ) {

            val (background, group, title, price, image, button) = createRefs()

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(227.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(
                        Color.White
                    )
                    .constrainAs(background) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )

            Text(
                text = "NIKE AIR",
                style = shoesTextStyle(),
                fontSize = 18.sp,
                color = ShoesBlack,
                modifier = Modifier
                    .padding(top = 24.dp, start = 24.dp)
                    .constrainAs(group) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )

            Text(
                text = "AIR JORDAN 1 MID SE GC",
                style = shoesTextStyle(),
                color = ShoesBlack,
                modifier = Modifier
                    .padding(start = 24.dp)
                    .constrainAs(title) {
                        top.linkTo(group.bottom)
                        start.linkTo(group.start)
                    }
            )

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "shoes",
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 88.dp)
                    .size(height = 225.dp, width = 224.dp)
            )

            Text(
                text = "$856",
                style = shoesTextStyle(),
                color = ShoesBlack,
                modifier = Modifier
                    .padding(start = 24.dp)
                    .constrainAs(price) {
                        top.linkTo(title.bottom)
                        start.linkTo(title.start)
                    }
            )

            Box(
                modifier = Modifier
                    .size(width = 83.dp, height = 76.dp)
                    .clip(RoundedCornerShape(topStart = 40.dp, bottomEnd = 40.dp))
                    .background(ShoesYellow)
                    .constrainAs(button) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(background.end)
                    }
                    .clickable {
                        onClickListener()
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "plus",
                    modifier = Modifier.align(Alignment.Center)
                        .size(34.dp)
                )
            }

        }
    }
}

fun shoesTextStyle() =
    TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(R.font.teko_regular))
    )

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeDesignTheme {
        ShoesMainScreen()
    }
}