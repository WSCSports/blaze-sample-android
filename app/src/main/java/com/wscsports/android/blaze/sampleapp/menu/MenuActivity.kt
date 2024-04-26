package com.wscsports.android.blaze.sampleapp.menu

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.wscsports.android.blaze.sampleapp.composepager.ComposePagerActivity
import com.wscsports.android.blaze.sampleapp.composerow.ComposeWithLazyRowActivity
import com.wscsports.android.blaze.sampleapp.viewpager.ViewPagerActivity


class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Button(onClick = { startActivity(Intent(this@MenuActivity, ViewPagerActivity::class.java)) }) {
                    Text(text = "StoriesWidgetsRowList in ViewPager")
                }
                Button(onClick = { startActivity(Intent(this@MenuActivity, ComposePagerActivity::class.java)) }) {
                    Text(text = "StoriesWidgetsRow in HorizontalPager")
                }
                Button(onClick = { startActivity(Intent(this@MenuActivity, ComposeWithLazyRowActivity::class.java)) }) {
                    Text(text = "LazyRow in HorizontalPager")
                }
            }
        }
    }
}