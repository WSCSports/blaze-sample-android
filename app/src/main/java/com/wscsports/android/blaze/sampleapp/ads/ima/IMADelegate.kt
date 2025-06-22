package com.wscsports.android.blaze.sampleapp.ads.ima

import android.util.Log
import com.blaze.blazesdk.ads.ima.BlazeIMAHandlerEventType
import com.blaze.blazesdk.ads.ima.models.BlazeImaAdInfo
import com.blaze.ima.BlazeIMAAdRequestInformation
import com.blaze.ima.BlazeIMADelegate
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings

object IMADelegate : BlazeIMADelegate {

    override suspend fun additionalIMATagQueryParams(requestData: BlazeIMAAdRequestInformation): Map<String, String> {
        // Any additional query params
        return emptyMap()
    }

    override suspend fun customIMASettings(requestData: BlazeIMAAdRequestInformation): ImaSdkSettings? {
        // Optional implementation. You can use this to customize ImaSdkSettings.
        return ImaSdkFactory.getInstance().createImaSdkSettings().apply {

        }
    }

    override fun onIMAAdError(errMsg: String) {
        Log.d("onIMAAdError", "errMsg - $errMsg")
    }

    override fun onIMAAdEvent(eventType: BlazeIMAHandlerEventType, adInfo: BlazeImaAdInfo?) {
        Log.d("onIMAAdEvent", "eventType - ${eventType.name}, adInfo - $adInfo")
    }

    override suspend fun overrideAdTagUrl(requestData: BlazeIMAAdRequestInformation): String? {
        // Optional implementation. You can use this to override the ad tag URL.
        return null
    }

}