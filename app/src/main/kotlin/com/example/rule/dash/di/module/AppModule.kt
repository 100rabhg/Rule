package com.example.rule.dash.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app : Application){

    @Provides
    fun provideContext(): Context = app.applicationContext

}