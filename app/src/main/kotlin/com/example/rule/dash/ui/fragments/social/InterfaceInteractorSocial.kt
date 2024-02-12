package com.example.rule.dash.ui.fragments.social

import com.example.rule.dash.di.PerActivity
import com.example.rule.dash.ui.activities.base.InterfaceInteractor

@PerActivity
interface InterfaceInteractorSocial<V : InterfaceViewSocial> : InterfaceInteractor<V> {

    fun valueEventSocial()
    fun valueEventEnablePermission()

}