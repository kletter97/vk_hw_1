package com.kletter.vk_hw_1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}



@Composable
fun MainScreen()
{
    var i: Int by rememberSaveable { mutableIntStateOf(0) }

    val configuration = LocalConfiguration.current

    val columnCount = integerResource(
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            R.integer.columns_landscape
        } else {
            R.integer.columns_portrait
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.ScreenPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columnCount),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(
                bottom = Dimens.ListBottomPadding
            ),
            horizontalArrangement = Arrangement.spacedBy(
                Dimens.ItemSpacing
            ),
            verticalArrangement = Arrangement.spacedBy(
                Dimens.ItemSpacing
            )
        ) {
            items(items = List(i) { it }) { index ->
                NumberCard(index = index+1)
            }
        }

        Button(
            onClick = { i += 1 },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.ButtonTopPadding)
        ) {
            Text(text = stringResource(R.string.add_button))
        }
    }

}

object Dimens
{
    val ScreenPadding = 12.dp
    val ItemSpacing = 8.dp
    val ListBottomPadding = 8.dp
    val ButtonTopPadding = 8.dp
}

@Composable
fun NumberCard(index: Int) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        color = colorResource(
            if (index % 2 == 0) {
                R.color.card_red
            } else {
                R.color.card_blue
            }
        ),
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = index.toString(),
                color = colorResource(R.color.card_text),
                fontSize = 24.sp,
            )
        }
    }
}
