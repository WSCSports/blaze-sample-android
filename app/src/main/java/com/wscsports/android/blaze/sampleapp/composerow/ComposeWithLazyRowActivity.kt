package com.wscsports.android.blaze.sampleapp.composerow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wscsports.android.blaze.sampleapp.R

class ComposeWithLazyRowActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pagerState = rememberPagerState(pageCount = {
                5
            })
            HorizontalPager(state = pagerState) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            if (it == 0) {
                                Color.Blue
                            } else {
                                Color.Red
                            }
                        )
                ) {

                    Text(text = "Pager: $it", modifier = Modifier.padding(20.dp), color = Color.White)

                    LazyRow(contentPadding = PaddingValues(10.dp)) {
                        items(12) {
                            Box {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = null,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .size(80.dp)
                                )
                                Text(modifier = Modifier.fillMaxWidth().align(Alignment.Center).padding(top = 10.dp), text = it.toString())
                            }
                        }
                    }

                }
            }
        }
    }
}