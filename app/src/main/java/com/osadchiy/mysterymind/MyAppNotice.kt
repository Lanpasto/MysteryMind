package com.example.mysterymind

import android.app.Application
import com.onesignal.OneSignal
// Replace the below with your own ONESIGNAL_APP_ID
const val ONESIGNAL_APP_ID = "85d778b2-14ea-4206-81a2-ca6c85ea783f"
class MyAppNotice : Application() {
    override fun onCreate() {
        super.onCreate()

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications();
    }
}