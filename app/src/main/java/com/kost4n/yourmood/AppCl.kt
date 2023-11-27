package com.kost4n.yourmood

import android.app.Application
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppCl: Application() {
    val ONESIGNAL_APP_ID = "59457cd6-b452-4d4e-bd96-5e78a5138bc8"
    override fun onCreate() {
        super.onCreate()

        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)

        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }

    }
}