package com.example.rule.dash.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.rule.dash.services.social.MonitorService
import com.example.rule.dash.utils.Consts.RESTART_MONITOR_RECEIVER
import com.pawegio.kandroid.IntentFor

class MonitorReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == RESTART_MONITOR_RECEIVER) context.startService(IntentFor<MonitorService>(context))
    }
}