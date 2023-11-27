package com.kost4n.yourmood.messaging

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.kost4n.yourmood.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


fun messageToKost(
    message: String,
    activity: MainActivity
) {
    val appName = "org.telegram.messenger"
    val myIntent = Intent(Intent.ACTION_SEND)
    myIntent.type = "text/plain"
    myIntent.setPackage(appName)
    myIntent.putExtra(Intent.EXTRA_TEXT, "YourMood: $message")
    activity.startActivity(Intent.createChooser(myIntent, "Share with"))
}

fun messageToKostMail(message: String) {
    GlobalScope.launch(Dispatchers.IO) {
        val USERNAME = "prohorenko.isvp@gmail.com"
        val PASSWORD = "prohorenko2005"
        val sender = EmailSender(USERNAME, PASSWORD)

        val createMessage = CreateMessage(sender)

        val sendEmail = SendEmail(sender, createMessage)
        sendEmail.send("prohorenko2005@gmail.com", "YourMood", "YourMood: $message!")
    }
}

fun openTelegram(activity: Activity, userName: String) {
    val general = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.com/$userName"))
    val generalResolvers = HashSet<String>()
    val generalResolveInfo = activity.packageManager.queryIntentActivities(general, 0)
    for (info in generalResolveInfo) {
        if (info.activityInfo.packageName != null) {
            generalResolvers.add(info.activityInfo.packageName)
        }
    }
    val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/$userName"))
    var goodResolver = 0
    // gets the list of intents that can be loaded.
    val resInfo = activity.packageManager.queryIntentActivities(telegram, 0)
    if (!resInfo.isEmpty()) {
        for (info in resInfo) {
            if (info.activityInfo.packageName != null && !generalResolvers.contains(info.activityInfo.packageName)) {
                goodResolver++
                telegram.setPackage(info.activityInfo.packageName)
            }
        }
    }
    //TODO: if there are several good resolvers create custom chooser
    if (goodResolver != 1) {
        telegram.setPackage(null)
    }
    if (telegram.resolveActivity(activity.packageManager) != null) {
        activity.startActivity(telegram)
    }
}
