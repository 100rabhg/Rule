package com.example.rule.dash.services.sms

import android.content.Intent
import com.example.rule.dash.services.base.BaseService
import com.example.rule.dash.utils.Consts.SMS_ADDRESS
import com.example.rule.dash.utils.Consts.SMS_BODY
import com.example.rule.dash.utils.Consts.TYPE_SMS
import javax.inject.Inject
class SmsService : BaseService(), InterfaceServiceSms {

    @Inject lateinit var interactor: InterfaceInteractorSms<InterfaceServiceSms>

    override fun onCreate() {
        super.onCreate()
        if (getComponent() != null) {
            getComponent()!!.inject(this)
            interactor.onAttach(this)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.setSmsIntent()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun Intent.setSmsIntent() {
        getStringExtra(SMS_ADDRESS)?.let { getStringExtra(SMS_BODY)?.let { it1 ->
            interactor.setPushSms(it,
                it1,getIntExtra(TYPE_SMS,0))
        } }
    }

    override fun stopServiceSms() {
        stopSelf()
    }

    override fun onDestroy() {
        interactor.onDetach()
        super.onDestroy()
    }


}