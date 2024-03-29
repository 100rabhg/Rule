package com.example.rule.dash.services.social

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.example.rule.dash.data.rxFirebase.InterfaceFirebase
import com.example.rule.dash.receiver.MonitorReceiver
import com.example.rule.dash.utils.Consts.CHILD_PERMISSION
import com.example.rule.dash.utils.Consts.CHILD_SOCIAL_MS
import com.example.rule.dash.utils.Consts.RESTART_MONITOR_RECEIVER
import com.example.rule.dash.utils.Consts.SOCIAL
import com.example.rule.dash.utils.Consts.TAG
import com.pawegio.kandroid.e
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
class InteractorMonitorService @Inject constructor(private val context: Context, private val firebase: InterfaceFirebase) : InterfaceMonitorService {

    @SuppressLint("CheckResult")
    override fun gerSocialStatus() {
        firebase.valueEvent("$SOCIAL/$CHILD_SOCIAL_MS")
                .map { data -> data.exists() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (!it) {
                        val intent = Intent(context, MonitorReceiver::class.java)
                        intent.action = RESTART_MONITOR_RECEIVER
                        context.sendBroadcast(intent)
                    }
                }, { e(TAG, it.message.toString()) })
    }

    override fun setPermission(status: Boolean) {
        firebase.getDatabaseReference("$SOCIAL/$CHILD_PERMISSION").setValue(status)
    }

}