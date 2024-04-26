package com.wscsports.android.blaze.sampleapp.composepager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.blaze.blazesdk.features.compose.StoriesWidgetsRow
import com.blaze.blazesdk.features.compose.WidgetStoriesStateHandler
import com.blaze.blazesdk.features.widgets.labels.BlazeDataSourceType
import com.blaze.blazesdk.features.widgets.labels.BlazeWidgetLabel
import com.blaze.blazesdk.presets.BlazeStoriesPresetThemes
import com.wscsports.android.blaze.sampleapp.core.Delegates

class ComposePagerActivity : ComponentActivity() {
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

                    StoriesWidgetsRow(
                        modifier = Modifier.height(120.dp)
                            .fillMaxWidth(), widgetStoriesStateHandler = WidgetStoriesStateHandler(
                            widgetId = "top-stories",
                            blazeStoryTheme = BlazeStoriesPresetThemes.ROW_WIDGET_CIRCLE,
                            dataSourceType = BlazeDataSourceType.Labels(BlazeWidgetLabel.atLeastOneOf("top-stories", "live-stories")),
                            widgetDelegate = Delegates.widgetDelegate
                        )
                    )

                }
            }
        }
    }
}