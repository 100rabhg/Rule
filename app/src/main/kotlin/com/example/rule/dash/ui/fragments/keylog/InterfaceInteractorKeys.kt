package com.example.rule.dash.ui.fragments.keylog

import com.example.rule.dash.di.PerActivity
import com.example.rule.dash.ui.activities.base.InterfaceInteractor
@PerActivity
interface InterfaceInteractorKeys<V : InterfaceViewKeys> : InterfaceInteractor<V> {
    fun valueEventEnableKeys()
}