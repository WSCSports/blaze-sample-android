package com.wscsports.android.blaze.sampleapp.moments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blaze.blazesdk.core.analytics.models.BlazeAnalyticsEvent
import com.blaze.blazesdk.core.models.BlazeResult
import com.blaze.blazesdk.features.stories.models.ui.CtaTypeModel
import com.blaze.blazesdk.features.widgets.labels.BlazeDataSourceType
import com.blaze.blazesdk.features.widgets.labels.BlazeWidgetLabel
import com.blaze.blazesdk.features.widgets.shared.GlobalDelegates
import com.blaze.blazesdk.presets.BlazeMomentPresetThemes
import com.wscsports.android.blaze.sampleapp.R
import com.wscsports.android.blaze.sampleapp.databinding.FragmentMomentsBinding
import com.wscsports.android.blaze.sampleapp.logd

class MomentsFragment : Fragment(R.layout.fragment_moments) {

    private var binding: FragmentMomentsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMomentsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRowWidget()
        initGridWidget()
        initGlobalDelegates()
    }

    override fun onResume() {
        super.onResume()
        binding?.momentsPullToRefresh?.isRefreshing = false
    }

    /**
     * Global delegates
     * */
    private fun initGlobalDelegates() {
        GlobalDelegates.onMomentsPlayerDidAppear = ::onMomentsPlayerDidAppear
        GlobalDelegates.onMomentsPlayerDismissed = ::onMomentsPlayerDismissed
        GlobalDelegates.onEventTriggered = ::onEventTriggered
    }

    /**
     * set and update the pullToRefresh layout Listener
     */
    private fun initListeners() {
        binding?.apply {
            momentsPullToRefresh.setOnRefreshListener {
                updateLabels()
                momentsPullToRefresh.isRefreshing = false
            }
        }
    }

    /**
     * Used to showcase options to change labels and or refresh data for current labelExpression.
     */
    private fun updateLabels() {
        binding?.apply {
//        binding.momentsRowWidget.updateLabel(BlazeWidgetLabel.singleLabel("moments"))
//        binding.momentsGridWidget.updateLabel(BlazeWidgetLabel.singleLabel("moments"))
            momentsRowWidget.reloadData()
            momentsGridWidget.reloadData()
        }
    }


    private fun initRowWidget() {
        // Using default Preset
        val momentsRowPreset = BlazeMomentPresetThemes.ROW_WIDGET_RECTANGLE
        //You can modify onboarding experience by setting firstTimeSlide in playerTheme
        //momentsRowPreset.playerTheme.firstTimeSlide.mainTitle.text ="Moments First Time Slide Title"

        //You can modify player buttons experience by setting buttons scaleType in playerTheme
        // momentsRowPreset.playerTheme.buttons.exitButton.scaleType = BlazeScaleType.FIT_XY

        binding?.momentsRowWidget?.initWidget(
            blazeMomentTheme = momentsRowPreset,
            dataSource = BlazeDataSourceType.Labels(BlazeWidgetLabel.singleLabel("moments")),
            widgetId = "moments-row",
            onItemClicked = { item ->
                logd("onItemClicked - item.id => ${item.id}")
            },
            onWidgetDataLoadStarted = ::onWidgetDataLoadStarted,
            onWidgetDataLoadCompleted = ::onWidgetDataLoadCompleted,
            onWidgetPlayerDismissed = ::onWidgetPlayerDismissed,
            onTriggerCTA = ::onTriggerCTA
        )
    }

    private fun initGridWidget() {
        // Using default Preset
        val momentsGridPreset = BlazeMomentPresetThemes.ROW_WIDGET_RECTANGLE

        //You can modify onboarding experience by setting firstTimeSlide in playerTheme
        //momentsGridPreset.playerTheme.firstTimeSlide.mainTitle.text ="Moments First Time Slide Title"

        //You can modify player buttons experience by setting buttons scaleType in playerTheme
        // momentsGridPreset.playerTheme.buttons.exitButton.scaleType = BlazeScaleType.FIT_XY

        //You can modify the end positioning of the bottom gradient.
        //momentsGridPreset.playerTheme.playerFooterGradient.endPositioning = PlayerFooterGradientPositioning.BOTTOM_TO_CONTAINER

        // We can modify the given presets. i.e.,
        // Limit the amount of items shown on the widget level
        momentsGridPreset.widgetLayout.maxDisplayItemsCount = 4

        binding?.momentsGridWidget?.initWidget(
            blazeMomentTheme = momentsGridPreset,
            dataSource = BlazeDataSourceType.Labels(BlazeWidgetLabel.singleLabel("moments")),
            widgetId = "moments-grid",
            onItemClicked = { item ->
                logd("onItemClicked - item.id => ${item.id}")
            },
            onWidgetDataLoadStarted = ::onWidgetDataLoadStarted,
            onWidgetDataLoadCompleted = ::onWidgetDataLoadCompleted,
            onWidgetPlayerDismissed = ::onWidgetPlayerDismissed,
            onTriggerCTA = ::onTriggerCTA
        )
    }


    private fun onWidgetDataLoadStarted(widgetId: String) {
        binding?.momentsPullToRefresh?.isRefreshing = true
        logd("onWidgetDataLoadStarted - widgetId => $widgetId")
    }

    private fun onWidgetDataLoadCompleted(
        widgetId: String,
        itemsCount: Int,
        error: BlazeResult<Any>?
    ) {
        logd("onWidgetDataLoadCompleted - widgetId => $widgetId, itemsCount => $itemsCount, error => $error")
        binding?.momentsPullToRefresh?.isRefreshing = false
    }

    private fun onWidgetPlayerDismissed(widgetId: String) {
        logd("onWidgetPlayerDismissed - widgetId => $widgetId")
    }

    private fun onTriggerCTA(widgetId: String, actionType: String, actionParam: String): Boolean {
        logd("onTriggerCTA - widgetId => $widgetId, actionType => $actionType, actionParam => $actionParam")

        return when (CtaTypeModel.typeFromString(actionType)) {
            CtaTypeModel.DEEPLINK -> {
                //return true as if this was handled by App and not SDK
                true
            }

            CtaTypeModel.WEB -> {
                //return true as if this was not handled by App and should be handled by SDK
                false
            }

            null -> {
                //Handle in case needed
                false
            }
        }
    }

    private fun onEventTriggered(eventData: BlazeAnalyticsEvent) {
        logd("onEventTriggered - eventType => ${eventData.event_action.value}, eventData => $eventData")
    }

    private fun onMomentsPlayerDidAppear() {
        logd("onMomentsPlayerDidAppear")
    }

    private fun onMomentsPlayerDismissed() {
        logd("onMomentsPlayerDismissed")
    }

}