package com.example.rule.dash.di.component

import com.example.rule.app.Dash
import com.example.rule.dash.data.rxFirebase.InterfaceFirebase
import com.example.rule.dash.di.module.AppModule
import com.example.rule.dash.di.module.FirebaseModule
import com.example.rule.dash.services.accessibilityData.AccessibilityDataService
import com.example.rule.dash.services.notificationService.NotificationService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, FirebaseModule::class])
interface AppComponent {

    fun inject(app: Dash)
    fun inject(accessibilityDataService: AccessibilityDataService)
    fun inject(notificationService: NotificationService)
    fun getInterfaceFirebase(): InterfaceFirebase

}