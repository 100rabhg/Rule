package com.example.rule.dash.ui.fragments.photo

import com.example.rule.dash.di.PerActivity
import com.example.rule.dash.ui.activities.base.InterfaceInteractor

@PerActivity
interface InterfaceInteractorPhoto<V : InterfaceViewPhoto> : InterfaceInteractor<V> {
    fun getPhotoCamera(facing:Int)
    fun valueEventEnablePhoto()
    fun valueEventEnablePermission()
}