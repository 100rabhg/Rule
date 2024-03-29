package com.example.rule.dash.ui.activities.base

import android.view.View
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.rule.dash.di.component.ActivityComponent
import com.example.rule.dash.ui.widget.toolbar.CustomToolbar
import com.tbruyelle.rxpermissions3.Permission
import com.tbruyelle.rxpermissions3.RxPermissions
import io.reactivex.disposables.Disposable

interface InterfaceView {

    fun setToolbar(toolbar: CustomToolbar,showSearch:Boolean,title:Int,showItemMenu:Int,hint:Int = 0)

    fun setActionToolbar(action:Boolean)

    fun getComponent(): ActivityComponent?

    fun getPermissions(): RxPermissions?

    fun subscribePermission(permission: Permission, msgRationale: String, msgDenied: String, granted: () -> Unit)

    fun showDialog(alertType: Int, title: Int, msg: String?, txtPositiveButton: Int?, cancellable: Boolean = false, action: SweetAlertDialog.() -> Unit) : SweetAlertDialog

    fun hideDialog()

    fun showError(message: String)

    fun showMessage(message: Int)

    fun showMessage(message: String)

    fun showSnackbar(message: Int,v:View)

    fun showSnackbar(message:String,v:View)

    fun addDisposable(disposable: Disposable)

    fun clearDisposable()

    fun successResult(result: Boolean, filter:Boolean = false)

    fun failedResult(throwable: Throwable)

    fun onItemClick(key: String?, child: String,file:String,position:Int)

    fun onItemLongClick(key: String?, child: String,file: String,position:Int)

    fun changeChild(fragmentTag:String)

}