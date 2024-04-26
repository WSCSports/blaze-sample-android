package com.wscsports.android.blaze.sampleapp.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blaze.blazesdk.features.widgets.labels.BlazeDataSourceType
import com.blaze.blazesdk.features.widgets.labels.BlazeWidgetLabel
import com.blaze.blazesdk.presets.BlazeStoriesPresetThemes
import com.wscsports.android.blaze.sampleapp.core.Delegates
import com.wscsports.android.blaze.sampleapp.databinding.FragmentInViewPagerBinding

class FragmentInViewPager : Fragment() {
    private lateinit var binding: FragmentInViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentInViewPagerBinding.inflate(inflater).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.blazeStories.initWidget(
            widgetId = "top-stories",
            blazeStoryTheme = BlazeStoriesPresetThemes.ROW_WIDGET_CIRCLE,
            dataSource = BlazeDataSourceType.Labels(BlazeWidgetLabel.atLeastOneOf("top-stories", "live-stories")),
            widgetDelegate = Delegates.widgetDelegate
        )
    }
}