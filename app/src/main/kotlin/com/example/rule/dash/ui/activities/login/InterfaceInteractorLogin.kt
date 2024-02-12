package com.example.rule.dash.ui.activities.login

import com.example.rule.dash.di.PerActivity
import com.example.rule.dash.ui.activities.base.InterfaceInteractor

@PerActivity
interface InterfaceInteractorLogin<V : InterfaceViewLogin> : InterfaceInteractor<V> {
    fun signInDisposable(email: String, pass: String)
}