package com.example.rule.dash.services.sms

import com.example.rule.dash.di.PerService
import com.example.rule.dash.services.base.InterfaceInteractorService

@PerService
interface InterfaceInteractorSms<S : InterfaceServiceSms> : InterfaceInteractorService<S> {

    fun setPushSms(smsAddress: String, smsBody: String,type: Int)

}