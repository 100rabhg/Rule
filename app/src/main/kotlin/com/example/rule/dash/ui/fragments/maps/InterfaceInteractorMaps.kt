package com.example.rule.dash.ui.fragments.maps

import com.example.rule.dash.di.PerActivity
import com.example.rule.dash.ui.activities.base.InterfaceInteractor
@PerActivity
interface InterfaceInteractorMaps<V : InterfaceViewMaps> : InterfaceInteractor<V> {
    fun valueEventLocation()
    fun valueEventEnableGps()
    fun valueEventEnablePermission()
}