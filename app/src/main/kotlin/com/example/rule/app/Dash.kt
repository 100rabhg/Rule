package com.example.rule.app

import android.app.Application
import androidx.multidex.MultiDex
import com.chrisplus.rootmanager.RootManager
import com.example.rule.dash.di.component.AppComponent
import com.example.rule.dash.di.component.DaggerAppComponent
import com.example.rule.dash.di.module.AppModule
import com.example.rule.dash.di.module.FirebaseModule
import com.example.rule.dash.utils.Consts.SIZE_CACHE_FIREBASE
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class Dash : Application() {

    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
        lateinit var root: RootManager
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).firebaseModule(FirebaseModule()).build()
        appComponent.inject(this)

        root = RootManager.getInstance()

        if (FirebaseApp.getApps(this).isNotEmpty()) {
            val database = FirebaseDatabase.getInstance()
            database.setPersistenceEnabled(true)
            database.setPersistenceCacheSizeBytes(SIZE_CACHE_FIREBASE)
        }

    }

}