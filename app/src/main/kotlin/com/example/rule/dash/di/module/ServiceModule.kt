package com.example.rule.dash.di.module

import android.app.Service
import android.content.Context
import com.example.rule.dash.di.PerService
import com.example.rule.dash.services.sms.InteractorSms
import com.example.rule.dash.services.sms.InterfaceInteractorSms
import com.example.rule.dash.services.sms.InterfaceServiceSms
import dagger.Module
import dagger.Provides
@Module
class ServiceModule(var service:Service) {

    @Provides
    fun provideContext(): Context = service.applicationContext

    @Provides
    @PerService
    fun provideInterfaceInteractorSms(interactor: InteractorSms<InterfaceServiceSms>): InterfaceInteractorSms<InterfaceServiceSms> = interactor

}