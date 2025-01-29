package com.wscsports.android.blaze.sampleapp.ads.gam

import android.os.Bundle
import android.util.Log
import com.blaze.blazesdk.ads.custom_native.BlazeGoogleCustomNativeAdsHandler
import com.blaze.gam.custom_native.BlazeCustomNativeAdData
import com.blaze.gam.custom_native.BlazeGAMCustomNativeAdsDelegate
import com.blaze.gam.custom_native.BlazeGamCustomNativeAdRequestInformation

object CustomNativeAdsDelegate : BlazeGAMCustomNativeAdsDelegate {

    override fun customGAMTargetingProperties(requestData: BlazeGamCustomNativeAdRequestInformation): Map<String, String> {
        return emptyMap()
    }

    override fun publisherProvidedId(requestData: BlazeGamCustomNativeAdRequestInformation): String? {
        return null
    }

    override fun networkExtras(requestData: BlazeGamCustomNativeAdRequestInformation): Bundle? {
        return null
    }

    override fun onGAMCustomNativeAdError(errMsg: String) {
        Log.d("CustomNativeAdsDelegate", "onGAMCustomNativeAdError: Error on ad - $errMsg")
    }

    override fun onGAMCustomNativeAdEvent(
        eventType: BlazeGoogleCustomNativeAdsHandler.EventType,
        adData: BlazeCustomNativeAdData
    ) {
        Log.d("CustomNativeAdsDelegate", "onGAMCustomNativeAdError: Received Ad event of type:  $eventType, for adData: $adData")
    }

}