package com.example.rule.dash.ui.fragments.notifications

import com.example.rule.dash.ui.activities.base.InterfaceInteractor

interface InterfaceInteractorNotifyMessage<V : InterfaceViewNotifyMessage> : InterfaceInteractor<V> {
    fun valueEventEnableNotifications()
}