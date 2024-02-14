package com.example.rule.dash.di.component

import com.example.rule.dash.di.PerService
import com.example.rule.dash.di.module.ServiceModule
import com.example.rule.dash.services.sms.SmsService
import com.example.rule.dash.services.social.MonitorService
import dagger.Component

@PerService
@Component(dependencies = [AppComponent::class], modules = [ServiceModule::class])
interface ServiceComponent {

    fun inject(smsService: SmsService)
    fun inject(monitorService: MonitorService)

}