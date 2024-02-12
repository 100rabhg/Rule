package com.example.rule.dash.ui.activities.register

import com.example.rule.dash.di.PerActivity
import com.example.rule.dash.ui.activities.base.InterfaceInteractor

@PerActivity
interface InterfaceInteractorRegister<V : InterfaceViewRegister> : InterfaceInteractor<V> {

    fun signUpDisposable(email: String, pass: String)

}